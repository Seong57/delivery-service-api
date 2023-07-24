package org.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
* spring 프로젝트는 자신의 하위에 있는 패키지에서 여러가지 어노테이션이 붙어있는 것들을 찾아서 빈으로 등록을 함.
* 그런데 멀티모듈 프로젝트를 함으로써 패키지가 다르고, group 이름도 다름
* 그래서 다른 프로젝트의 빈을 가져오지 못하므로 빈으로 등록하기 위한 설정이 필요.
*
* 또는 각 프로젝트의 패키지명(org.delivery.api)을 동일한 이름으로 맞추어주면 설정 없이 빈으로 등록 가능.
*
*/

/*@Configuration
@EntityScan(basePackages = "org.delivery.db")   // 주소 하위의 @Entity 가 붙은 클래스를 스캔
@EnableJpaRepositories(basePackages = "org.delivery.db")    //주소 하위의 Repository 도 사용한단 어노테이션
public class JpaConfig {


}*/
