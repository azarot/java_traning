package com.azarot.homework.hw18.bpp;

import com.azarot.homework.hw18.annotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var wrappedBean = bean;
        if (bean.getClass().getAnnotation(Trimmed.class) != null) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(getMethodInterceptor());
            wrappedBean = enhancer.create();
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(wrappedBean, beanName);
    }

    private MethodInterceptor getMethodInterceptor() {
        return (object, method, arguments, methodProxy) -> {
            var trimmedArguments = Arrays.stream(arguments)
                    .map(TrimmedAnnotationBeanPostProcessor::trim)
                    .toArray();
            return trim(methodProxy.invokeSuper(object, trimmedArguments));

        };
    }

    private static Object trim(Object object) {
        if (object == null) return null;

        return object instanceof String ? ((String) object).trim() : object;
    }
}

