package io.celsoagra.command.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {

	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.celsoagra")).build().apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("spring-boot-interview")
				.description("POC Spring Boot Interview, utilizando Rabbit MQ, MongoDB e Wiremock").version("0.1.0").license("GPLv3")
				.licenseUrl("https://tldrlegal.com/license/gnu-general-public-license-v3-(gpl-3)").build();
	}
}