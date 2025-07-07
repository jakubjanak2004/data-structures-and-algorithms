package dsa.functionalProgramming.Promise.PromiseState;

import dsa.functionalProgramming.Promise.Promise;

import java.util.function.Consumer;
import java.util.function.Function;

public class RejectedPromiseState<T> extends PromiseState<T> {
    public RejectedPromiseState(Promise<T> promise) {
        super(promise);
        for (Consumer<Throwable> onFailureIteratedConsumer : promise.getOnFailureConsumerList()) {
            onFailureIteratedConsumer.accept(promise.getThrowable());
        }
        if (promise.getFinallyCallRunnable() != null) {
            promise.getFinallyCallRunnable().run();
        }
    }

    @Override
    public Promise<T> newPromise() {
        return new Promise<>((onSuccess, onFailure) -> onFailure.accept(promise.getThrowable()));
    }

    @Override
    public void accept(Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        onFailure.accept(promise.getThrowable());
    }

    @Override
    public <R> Promise<R> apply(Function<T, R> onSuccess, Consumer<Throwable> onFailure) {
        return null;
    }

    @Override
    public void finallyCall(Runnable finallyRunnable) {
        finallyRunnable.run();
    }

    @Override
    public T get() throws Throwable {
        throw promise.getThrowable();
    }

    @Override
    public void toFulfilledState() {
        promise.setPromiseState(new FulfilledPromiseState<>(promise));
    }

    @Override
    public void toRejectedState() {
    }
}
