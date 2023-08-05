package org.delivery.apigw.filter

import org.delivery.apigw.account.model.TokenDto
import org.delivery.apigw.account.model.TokenValidationRequest
import org.delivery.apigw.account.model.TokenValidationResponse
import org.delivery.apigw.common.Log
import org.delivery.commom.error.TokenErrorCode
import org.delivery.commom.exception.ApiException
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.lang.reflect.ParameterizedType

@Component
class ServiceApiPrivateFilter: AbstractGatewayFilterFactory<ServiceApiPrivateFilter.Config>(Config::class.java) {

    companion object: Log

    class Config

    override fun apply(config: Config): GatewayFilter {

        return GatewayFilter { exchange, chain ->

            val uri = exchange.request.uri
            log.info("service api private filter route uri : {} ", uri)

            // account sever를 통한 인증 실행

            // 1. 토큰의 유무 확인
            val headers = exchange.request.headers["authorization-token"]?: listOf()

            val token = if (headers.isEmpty()){
                throw ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND)
            }else{
                headers[0]
            }
            log.info("authorization-token : {}", token)

            // 2. 토큰 유효성

            val accountApiUrl = UriComponentsBuilder
                .fromUriString("http://localhost")
                .port(8082)
                .path("/internal-api/token/validation")
                .build()
                .encode()
                .toUriString()

            val webclient = WebClient.builder().baseUrl(accountApiUrl).build()

            val request = TokenValidationRequest(
                tokenDto = TokenDto(
                    token = token,
                )
            )
            webclient
                .post()
                .body(Mono.just(request), object : ParameterizedTypeReference<TokenValidationRequest>(){})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                    {
                        status: HttpStatus -> status.isError
                    },
                    {
                        response: ClientResponse -> response.bodyToMono(object :ParameterizedTypeReference<Any>(){})
                        .flatMap { error ->
                            log.error("", error)
                            Mono.error(ApiException(TokenErrorCode.TOKEN_EXCEPTION))
                        }
                    }
                )
                .bodyToMono(object : ParameterizedTypeReference<TokenValidationResponse>(){})
                .flatMap { response ->
                    // 응답이 왔을 때
                    log.info("response : {}", response)

                    // 3. 사용자 정부 추가3

                    val userId = response.userId?.toString()

                    val proxyRequest = exchange.request.mutate()
                        .header("x-user-id", userId)
                        .build()

                    val requestBuild = exchange.mutate().request(proxyRequest).build()

                    val mono = chain.filter(requestBuild)

                    mono
                }
                .onErrorMap {e ->
                    log.info("", e)
                    e
                }
        }
    }
}