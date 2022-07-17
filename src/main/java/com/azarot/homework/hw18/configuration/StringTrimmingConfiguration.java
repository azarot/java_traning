package com.azarot.homework.hw18.configuration;

import com.azarot.homework.hw18.bpp.TrimmedAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringTrimmingConfiguration {

    @Bean
    public TrimmedAnnotationBeanPostProcessor createdTrimmedAnnotationBeanPostProcessor() {
        return new TrimmedAnnotationBeanPostProcessor();
    }
}
