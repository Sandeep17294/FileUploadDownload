/*
 * package com.aetins.core.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.ComponentScan; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
 * 
 * import com.fasterxml.jackson.databind.DeserializationFeature; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.fasterxml.jackson.databind.SerializationFeature;
 * 
 * @Configuration
 * 
 * @ComponentScan(basePackages = "com.aetins") public class
 * ApplicationConfiguration {
 * 
 * @Bean public MappingJackson2HttpMessageConverter
 * customJackson2HttpMessageConverter() { MappingJackson2HttpMessageConverter
 * jsonConverter = new MappingJackson2HttpMessageConverter(); ObjectMapper
 * objectMapper = new ObjectMapper();
 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
 * false); objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
 * false); jsonConverter.setObjectMapper(objectMapper); return jsonConverter; }
 * 
 * 
 * }
 */