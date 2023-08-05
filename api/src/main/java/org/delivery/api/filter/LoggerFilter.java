package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    /*@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("INIT URI : {}",req.getRequestURI());

        //doFilter 를 기준으로 위에는 req 아래는 res
        chain.doFilter(req, res);

        //request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            //authorization-token : ???, user-agent : ???
            headerValues.append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(",")
                    .append("] ");

        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>>> uri : {}, method : {}, header : {}, body : {}",uri, method ,headerValues, requestBody);

        //response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(",")
                    .append("] ");

        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<< uri : {}, method : {}, header : {}, body : {}",uri ,method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        Enumeration<String> requestHeaderNames = req.getHeaderNames();
        StringBuilder headerValues;
        headerValues = new StringBuilder();

        requestHeaderNames.asIterator().forEachRemaining(headerName -> {
            var headerValue = req.getHeader(headerName);

            headerValues.append("[")
                    .append(headerName)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        var uri = req.getRequestURI();
        var method = req.getMethod();
        var requestBody = new String(req.getContentAsByteArray());

        log.info("uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);

        // response

        var responseHeaderNames = res.getHeaderNames();
        var responseHeaderValues = new StringBuilder();

        responseHeaderNames.forEach(headerName -> {
            var headerValue = res.getHeader(headerName);

            responseHeaderValues.append("[")
                    .append(headerName)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
