package com.interstellar.devopsjenkins.FeignClient;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class FeignBuilderConfig {

    @Bean
    public JenkinsFeign getJenkinsFeign() {
        return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(JenkinsFeign.class, "http://localhost:8080");
    }

    @Bean
    public JenkinsFeign1 getJenkinsFeign1() {
        return Feign.builder().target(JenkinsFeign1.class, "http://localhost:8080");
    }

    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) && !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }
}
