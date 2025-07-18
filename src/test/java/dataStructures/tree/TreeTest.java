package dataStructures.tree;

import dsa.dataStructures.tree.Tree;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// todo make sure the trees works for doubles as well, now it does not, convert the comparison to double not integer
public class TreeTest {
    private final Reflections reflections = new Reflections("dsa.dataStructures.tree");
    private final Random random = new Random();

    @TestFactory
    public Stream<DynamicTest> generateTreeTests() {
        return reflections.getSubTypesOf(Tree.class).stream()
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .map(this::insertLotOfElementsAndTestContains);
    }

    private DynamicTest insertLotOfElementsAndTestContains(Class<?> clazz) {
        return DynamicTest.dynamicTest("testing tree: " + clazz.getSimpleName(), () -> {
            List<Integer> elementList = new ArrayList<>();
            Tree<Integer> tree;
            try {
                tree = (Tree<Integer>) clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            assertEquals(0, tree.size());
            assertFalse(tree.contains(5));

            // todo bTree: 128 passing 129 not
            for (int i = 0; i < 5_000; i++) {
                elementList.add(i);
                tree.add(i);
                assertTrue(tree.contains(i));
                assertEquals(i + 1, tree.size());
            }
        });
    }
}
