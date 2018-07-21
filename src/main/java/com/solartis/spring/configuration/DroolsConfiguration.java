package com.solartis.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({ "com.solartis.spring.configuration" })
@ImportResource("classpath:properties/drools-context.xml")
public class DroolsConfiguration {

}
