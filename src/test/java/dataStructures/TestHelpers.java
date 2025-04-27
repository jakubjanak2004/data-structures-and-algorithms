package dataStructures;

import org.junit.jupiter.params.provider.Arguments;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestHelpers {
    public static List<Class<?>> findAllNonAbstractChildrenForClass(String packageName, Class<?> clazz) {
        Reflections reflections = new Reflections(packageName);

        return reflections.getSubTypesOf(clazz)
                .stream()
                .filter(oneClass -> !Modifier.isAbstract(oneClass.getModifiers()))
                .collect(Collectors.toList());
    }

    public static <T> Stream<Arguments> instantiateAllChildrenWithEmptyConstructor(List<Class<?>> classes) {
         return classes.stream().map(clazz -> {
            try {
                T instance = (T) clazz.getDeclaredConstructor().newInstance();
                return Arguments.of(instance, clazz.getSimpleName());
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate " + clazz.getName(), e);
            }
        });
    }

    public static Stream<Arguments> getInstantiatedChildrenForClass(String packageName, Class<?> clazz) {
        return instantiateAllChildrenWithEmptyConstructor(
                findAllNonAbstractChildrenForClass(
                        packageName, clazz
                )
        );
    }
}
