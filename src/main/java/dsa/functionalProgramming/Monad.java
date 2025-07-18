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

    public static <R> Monad<R> of(R value) {
        if (value == null) throw new NullPointerException("Value cannot be null");
        return new Monad<>(value);
    }

    public static <R> Monad<R> ofNullable(R value) {
        if (value == null) return Monad.empty();
        return new Monad<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <R> Monad<R> empty() {
        return (Monad<R>) EMPTY;
    }

    public <R> Monad<R> map(Function<? super T, ? extends R> f) {
        if (value == null) return empty();
        return new Monad<>(f.apply(value));
    }

    public <R> Monad<R> flatMap(Function<? super T, Monad<? extends R>> f) {
        if (value == null) return empty();
        R computedValue = f.apply(value).orElse(null);
        return new Monad<>(computedValue);
    }

    public T get() {
        return value;
    }

    public void ifPresent(Consumer<? super T> consumer) {
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
