package org.delivery.api.config.rabbitmq;

/*

@Configuration
public class RabbitMqConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("delivery.exchange");
    }

    @Bean
    public Queue queue(){
        return new Queue("delivery.queue");
    }

    */
/*
    * 스프링에서 매개변수에 특별한 어노테이션이 붙지 않았어도 객체라면 제일 먼저 빈 스코프로 인해
    * 위에서 만들어진 queue 와 exchange 가 매개변수로 넘어가게 된다.
    *//*

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }



    // end queue 설정

    // object -> json
    // json -> object 로 바꿔주는 것이 메세지 컨버터
    @Bean
    public RabbitTemplate rabbitTemplate(
        // 커넥션 팩토리는 리소스 하위의 어플리케이션.yaml 파일의 rabbitmq 설정을 해둠.
        // rabbitmq 에서 커넥션 팩토리를 자동으로 만들어 yaml 파일의 정보를 채워 넣어줌
        ConnectionFactory connectionFactory,
        MessageConverter messageConverter
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    */
/*ObjectMapper 를 Bean 으로 설정해두었기 때문에 설정해둔 빈이 매개변수로 들어오게 됨.*//*

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
*/
