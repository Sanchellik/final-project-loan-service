//package ru.gozhan.loanservice.config;
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Collections;
//
//@Configuration
//public class ApplicationConfig {
//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
//        return builder.messageConverters(converter).build();
//    }
//
//}
