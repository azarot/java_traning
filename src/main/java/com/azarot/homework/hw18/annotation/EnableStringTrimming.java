package com.azarot.homework.hw18.annotation;

import com.azarot.homework.hw18.configuration.TrimmingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TrimmingConfiguration.class)
public @interface EnableStringTrimming {
}