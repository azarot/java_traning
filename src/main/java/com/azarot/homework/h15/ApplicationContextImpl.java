package com.azarot.homework.h15;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContextImpl implements ApplicationContext {
    private final List<BeanContainer> beans;

    public ApplicationContextImpl(String rootPackage) {
        Set<Class> allClasses = ReflectionUtils.findAllClasses(rootPackage);

        beans = allClasses.stream()
                .filter(c -> Arrays.stream(c.getAnnotations()).anyMatch(a -> a.annotationType().equals(Bean.class)))
                .map(this::createBeanInstance)
                .toList();
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        return (T) beans.stream()
                .filter(b -> b.beanInstance().getClass().equals(beanType))
                .findFirst()
                .map(BeanContainer::beanInstance)
                .orElseThrow();
    }

    private <T> BeanContainer createBeanInstance(Class<T> beanType) {
        String name = beanType.getAnnotation(Bean.class).value();
        if (name.isEmpty()) {
            name = beanType.getName();
        }

        return new BeanContainer(name, createInstance(beanType));
    }

    private <T> T createInstance(Class<T> beanType) {
        Constructor<T> constructor = null;
        try {
            constructor = beanType.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                    String.format("Cannot create bean %s because of missing default constructor", beanType));
        }

        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    String.format("Cannot create bean %s because of private default constructor", beanType));
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> beanType) {
        return null;
    }

    @Override
    public <T> Map<String, T> getAllBeans(Class<T> beanType) {
        return null;
    }
}

record BeanContainer(String name, Object beanInstance) {

}
