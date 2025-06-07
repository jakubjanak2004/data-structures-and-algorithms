package utils;

import org.junit.jupiter.params.provider.Arguments;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Random;
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

    public static int[] getIntArray(int numberOfElements, int min, int max) {
        Random random = new Random();
        return random.ints(numberOfElements, min, max + 1).toArray();
    }

    public static double[] getDoubleArray(int numberOfElements, int min, int max) {
        Random random = new Random();
        return random.doubles(numberOfElements, min, max).toArray();
    }

    public static Integer[] getIntegerObjectArray(int numberOfElements, int min, int max) {
        int[] array = getIntArray(numberOfElements, min, max);
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i]; // auto-boxing
        }
        return result;
    }

    public static Double[] getDoubleObjectArray(int numberOfElements, int min, int max) {
        double[] array = getDoubleArray(numberOfElements, min, max);
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
