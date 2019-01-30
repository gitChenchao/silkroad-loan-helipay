package com.danning.silkroad.helipay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 类名称：Swagger2Config<br>
 * 类描述：<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.danning.silkroad.helipay"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("澹宁科技-合利宝RESTful APIs")
                .description("澹宁科技-合利宝")
                .termsOfServiceUrl("")
                .contact(new Contact("陈超", "", "chenchao_java@163.com"))
                .version("1.0")
                .build();
    }
}
