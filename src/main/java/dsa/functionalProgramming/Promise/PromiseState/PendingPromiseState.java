package dsa.functionalProgramming.Promise.PromiseState;

import dsa.functionalProgramming.Promise.Promise;

import java.util.function.Consumer;
import java.util.function.Function;

public class PendingPromiseState<T> extends PromiseState<T> {
    private boolean wasConsumerRan = false;

    public PendingPromiseState(Promise<T> promise) {
        super(promise);
    }

    @Override
    public Promise<T> newPromise() {
        return new Promise<>(promise::thenAccept);
    }

    @Override
    public void accept(Consumer<T> onSuccessUserConsumer, Consumer<Throwable> onFailureUserConsumer) {
        // add the callbacks into Lists for later calling
        promise.getOnSuccessConsumerList().add(onSuccessUserConsumer);
        promise.getOnFailureConsumerList().add(onFailureUserConsumer);

        callConsumer();
    }

    // todo
    @Override
    public <R> Promise<?> apply(Function<T, R> onSuccess, Consumer<Throwable> onFailure) {
//        promise.getOnSuccessFunctionList().add(onSuccess);
//        promise.getOnFailureConsumerList().add(onFailure);
//
//        Promise<?> returnPromise = new Promise<>((onSuccessInPromise, onFailureInPromise) ->
//                promise.thenAccept(onSuccessInPromise, onFailureInPromise));
//
//        callConsumer();
//        return returnPromise;
        return null;
    }

    @Override
    public void finallyCall(Runnable finallyRunnable) {
        promise.setFinallyCallRunnable(finallyRunnable);
    }

    @Override
    public synchronized void callConsumer() {
        // if the Consumer is not running execute it
        if (wasConsumerRan) return;

        Consumer<T> onSuccess = val -> {
            promise.setValue(val);
            toFulfilledState();
            promise.getDone().countDown();
        };

        Consumer<Throwable> onFailure = thr -> {
            promise.setThrowable(thr);
            toRejectedState();
            promise.getDone().countDown();
        };
        wasConsumerRan = true;
        Thread.startVirtualThread(() -> promise.getPromiseConsumer().accept(onSuccess, onFailure));
    }

    @Override
    public T get() throws Throwable {
        throw new IllegalStateException("Pending state cannot return a value!");
    }

    @Override
    public void toFulfilledState() {
        promise.setPromiseState(new FulfilledPromiseState<>(promise));
    }

    @Override
    public void toRejectedState() {
        promise.setPromiseState(new RejectedPromiseState<>(promise));
    }
}
