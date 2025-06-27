package dsa.functionalProgramming;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A container object which may or may not contain a non-null value.
 * This class provides methods to handle the presence or absence of a value
 * in a functional and null-safe way.
 *
 * @param <T> the type of the wrapped object
 */
public class Monad<T> {
    private static final Monad<?> EMPTY = new Monad<>();
    private final T value;

    private Monad() {
        this.value = null;
    }

    private Monad(T value) {
        this.value = value;
    }

    public static <U> Monad<U> of(U value) {
        if (value == null) throw new NullPointerException();
        return new Monad<>(value);
    }

    public static <U> Monad<U> ofNullable(U value) {
        if (value == null) return Monad.empty();
        return new Monad<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <U> Monad<U> empty() {
        return (Monad<U>) EMPTY;
    }

    public <U> Monad<U> map(Function<T, U> f) {
        if (value == null) return empty();
        return new Monad<>(f.apply(value));
    }

    public <U> Monad<U> flatMap(Function<T, Monad<U>> f) {
        if (value == null) return empty();
        return f.apply(value);
    }

    public T get() {
        return value;
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value == null) return;
        consumer.accept(value);
    }

    public T orElse(T other) {
        if (value == null) return other;
        return value;
    }

    public T orElseThrow(Supplier<Throwable> throwableSupplier) throws Throwable {
        if (value == null) throw throwableSupplier.get();
        return value;
    }
}
