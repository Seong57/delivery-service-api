package org.delivery.api.config.objectMapper;

/*
@Configuration
public class ObjectMapperConfig {


    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module());  //jdk 8 버전 이후 클래스

        objectMapper.registerModule(new JavaTimeModule());  // local date

        //모르는 JSON field 에대해서는 무시한다.
        //ex) AccountDto 의 필드를 제외하고 다른 데이터를 보냈다면 에러를 터트릴거냐에 대한 설정
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //날짜 관련 직렬화
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //snake case
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}
*/
