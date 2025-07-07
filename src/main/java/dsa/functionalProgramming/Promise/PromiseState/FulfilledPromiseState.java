package dsa.functionalProgramming.Promise.PromiseState;

import dsa.functionalProgramming.Promise.Promise;

import java.util.function.Consumer;
import java.util.function.Function;

public class FulfilledPromiseState<T> extends PromiseState<T> {
    public FulfilledPromiseState(Promise<T> promise) {
        super(promise);
        for (Consumer<T> onSuccessIteratedConsumer : promise.getOnSuccessConsumerList()) {
            onSuccessIteratedConsumer.accept(promise.getValue());
        }
        if (promise.getFinallyCallRunnable() != null) {
            promise.getFinallyCallRunnable().run();
        }
    }

    @Override
    public Promise<T> newPromise() {
        return new Promise<>((onSuccess, onFailure) -> onSuccess.accept(promise.getValue()));
    }

    @Override
    public void accept(Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        onSuccess.accept(promise.getValue());
    }

    @Override
    public <R> Promise<?> apply(Function<T, R> onSuccess, Consumer<Throwable> onFailure) {
        R returnedValue = onSuccess.apply(promise.getValue());
        if (returnedValue instanceof Promise<?>) {
            return (Promise<?>) returnedValue;
        }
        return new Promise<R>((onSuccessInPromise, onFailureInPromise) -> {
            onSuccessInPromise.accept(returnedValue);
        });
    }

    @Override
    public void finallyCall(Runnable finallyRunnable) {
        finallyRunnable.run();
    }

    @Override
    public T get() throws Throwable {
        return promise.getValue();
    }

    @Override
    public void toFulfilledState() {
    }

    @Override
    public void toRejectedState() {
        promise.setPromiseState(new RejectedPromiseState<>(promise));
    }
}
