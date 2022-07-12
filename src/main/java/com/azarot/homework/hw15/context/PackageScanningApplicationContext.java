package com.azarot.homework.hw15.context;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.annotation.Inject;
import com.azarot.homework.hw15.exception.NoSuchBeanException;
import com.azarot.homework.hw15.exception.NoUniqueBeanException;
import org.reflections.Reflections;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.azarot.homework.hw15.utils.StringUtils.lowerCaseFirstCharacter;
import static com.azarot.homework.hw15.utils.StringUtils.upperCaseFirstCharacter;


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
        injectFields();
    }

    private void injectFields() {
        for (var instance : container.values()) {
            Class<?> instanceClass = instance.getClass();

            Arrays.stream(instanceClass.getDeclaredFields())
                    .filter(f -> f.getAnnotationsByType(Inject.class).length != 0)
                    .forEach(insertFields(instance, instanceClass));

        }
    }

    private Consumer<Field> insertFields(Object instance, Class<?> instanceClass) {
        return f -> {
            var setterName = "set" + upperCaseFirstCharacter(f.getName());
            var bean = getBean(f.getType());
            getSetter(instanceClass, f, setterName).ifPresentOrElse(
                    setWithSetter(instance, bean),
                    setDirectlyToField(instance, f, bean)
            );
        };
    }

    private Runnable setDirectlyToField(Object instance, Field field, Object value) {
        return () -> {
            try {
                field.setAccessible(true);
                field.set(instance, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private Consumer<Method> setWithSetter(Object object, Object value) {
        return m -> {
            try {
                m.invoke(object, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private Optional<Method> getSetter(Class<?> beanClass, Field f, String setterName) {
        try {
            return Optional.of(beanClass.getMethod(setterName, f.getType()));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
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
        return lowerCaseFirstCharacter(beanClass.getSimpleName());
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