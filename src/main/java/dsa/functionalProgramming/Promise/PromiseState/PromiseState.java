package dsa.functionalProgramming.Promise.PromiseState;

import dsa.functionalProgramming.Promise.Promise;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class PromiseState<T> {
    protected final Promise<T> promise;

    public PromiseState(Promise<T> promise) {
        this.promise = promise;
    }

    abstract public Promise<T> newPromise();

    abstract public void accept(Consumer<T> onSuccess, Consumer<Throwable> onFailure);

    abstract public <R> Promise<?> apply(Function<T, R> onSuccess, Consumer<Throwable> onFailure);

    abstract public void finallyCall(Runnable finallyConsumer);

    abstract public T get() throws Throwable;

    abstract public void toFulfilledState();

    abstract public void toRejectedState();

    public void callConsumer() {

    }
}