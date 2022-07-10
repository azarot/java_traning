package com.azarot.homework.h15.context;

import com.azarot.homework.h15.BoboBean;
import com.azarot.homework.h15.exception.NoSuchBeanException;
import com.azarot.homework.h15.exception.NoUniqueBeanException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class PackageScanningApplicationContext implements ApplicationContext {

    private final Map<String, Object> container = new HashMap<>();

    public PackageScanningApplicationContext(String packageName) {
        //1: find all classes in the package
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> beanClasses = reflections.getTypesAnnotatedWith(BoboBean.class);

        //2: create instance of @Bean class
        for (Class<?> beanClass : beanClasses) {
            var constructor = getConstructor(beanClass);
            var instance = createInstance(constructor);

            //3: get name for created instance
            String beanName = getBeanName(beanClass);

            storeBeanInstance(beanName, instance);
        }


        //3: set autowired fields
        //TODO: implement it
    }

    private void storeBeanInstance(String beanName, Object instance) {
        if (container.containsKey(beanName)) {
            throw new RuntimeException("Several beans with name: " + beanName);
        }
        container.put(beanName, instance);
    }

    private String getBeanName(Class<?> beanClass) {
        String value = beanClass.getAnnotation(BoboBean.class).value();
        return value.isBlank() ? getDefaultName(beanClass) : value;
    }

    private String getDefaultName(Class<?> beanClass) {
        String simpleName = beanClass.getSimpleName();
        String firstLetter = simpleName.substring(0, 1);
        return firstLetter.toLowerCase() + simpleName.substring(1);
    }

    private Object createInstance(Constructor<?> constructor) {
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Can't create instance", e);
        }
    }

    private Constructor<?> getConstructor(Class<?> beanClass) {
        try {
            return beanClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No default constructor for " + beanClass.getName());
        }
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        List<Object> instances = container.values().stream()
                .filter(i -> beanType.isAssignableFrom(i.getClass()))
                .toList();

        if (instances.size() == 0) {
            throw new NoSuchBeanException();
        } else if (instances.size() > 1) {
            throw new NoUniqueBeanException();
        } else {
            return beanType.cast(instances.get(0));
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> beanType) {
        Object instance = container.get(name);
        if (instance == null || !beanType.isAssignableFrom(instance.getClass())) {
            throw new NoSuchBeanException();
        }
        return beanType.cast(instance);
    }

    @Override
    public <T> Map<String, T> getAllBeans(Class<T> beanType) {
        return container.entrySet().stream()
                .filter(e -> beanType.isAssignableFrom(e.getValue().getClass()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> beanType.cast(e.getValue())));
    }
}