package com.vaadin.flow.tutorial.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
// code for spring/tutorial-spring-configuration.asciidoc
public class MyConfiguration {

}
