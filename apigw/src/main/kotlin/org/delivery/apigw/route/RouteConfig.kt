package org.delivery.apigw.route

import org.delivery.apigw.filter.ServiceApiPrivateFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val serviceApiPrivateFilter: ServiceApiPrivateFilter
) {

    @Bean
    fun gateWayRoutes(builder: RouteLocatorBuilder): RouteLocator{
        return builder.routes()
            .route { spec ->
                spec.order(-1)  // 우선순위 지정: 최우선
                spec.path(
                    "/service-api/api/**"   // 매칭할 주소
                ).filters { filterSpec ->
                    filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config()))  // 필터 지정
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080" // 라우팅할 주소
                )

            }
        .build()
    }
}