package com.neperix.hobnob.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.neperix.hobnob.rest.ArticleController;

@ComponentScan(basePackageClasses = ArticleController.class)
@Configuration
public class PostingRestConfig {

}
