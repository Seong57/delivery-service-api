package org.delivery.storeadmin.config.objectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    /*registerModule() 메소드는 ObjectMapper 인스턴스에 추가 기능(모듈)을 등록하는데 사용됩니다.
    위 코드에서는 Jdk8Module을 ObjectMapper에 등록하고 있습니다.
    Jdk8Module은 Java 8과 관련된 다양한 기능들을 지원하며,
    이를 ObjectMapper에 등록함으로써 Java 8의 Optional, LocalDate,
    LocalDateTime 등과 같은 다양한 타입을 처리할 수 있습니다.
    registerModule() 메소드를 사용해 다른 모듈도 등록할 수 있으며,
    이를 통해 ObjectMapper가 다양한 데이터 타입 및 기능을 지원하는 방법을
    커스터마이징할 수 있습니다.
    요약하면,registerModule() 메소드는 ObjectMapper에 새로운 기능을 제공하는 모듈을 등록하는 메소드입니다.
    여기서는 Java 8 관련 기능을 지원하는 Jdk8Module을 등록하고 있습니다.*/
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module());  //jdk 8 버전 이후 클래스

        objectMapper.registerModule(new JavaTimeModule());  // local date

        //모르는 JSON field 에 대해서는 무시한다.
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
