package org.delivery.api.config.web;

/*

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthorizationInterceptor authorizationInterceptor;
    private final UserSessionResolver userSessionResolver;
    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER)
                ;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userSessionResolver);
    }
}
*/
