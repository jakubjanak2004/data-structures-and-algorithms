package dsa.functionalProgramming.Promise;

import dsa.functionalProgramming.Promise.PromiseState.PendingPromiseState;
import dsa.functionalProgramming.Promise.PromiseState.PromiseState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

// todo make sure the promise behaves like the JS promise
// todo if callback in then returns value promise behave accordingly
// todo think about await being a internal functionality, when then is called it is always with await
public class Promise<T> {
    private final CountDownLatch done = new CountDownLatch(1);
    private final BiConsumer<Consumer<T>, Consumer<Throwable>> promiseConsumer;
    private final List<Consumer<T>> onSuccessConsumerList = Collections.synchronizedList(new ArrayList<>());
    private final List<Function<T, ?>> onSuccessFunctionList = Collections.synchronizedList(new ArrayList<>());
    private final List<Consumer<Throwable>> onFailureConsumerList = Collections.synchronizedList(new ArrayList<>());
    // todo this does not work greatly as can be chained now, will skip running runnable when in pending
    private Runnable finallyCallRunnable;
    private T value;
    private Throwable throwable;
    private PromiseState<T> promiseState;

    public Promise(BiConsumer<Consumer<T>, Consumer<Throwable>> promiseConsumer) {
        this.promiseConsumer = promiseConsumer;
        promiseState = new PendingPromiseState<>(this);
    }

    public Promise<T> thenAccept(Consumer<T> onSuccessUserConsumer, Consumer<Throwable> onFailureUserConsumer) {
        // state accept calling
        promiseState.accept(onSuccessUserConsumer, onFailureUserConsumer);

        // returning new Promise
        return promiseState.newPromise();
    }

    // todo if R is a promise return that if not wrap the value in promise, automatic flat-mapping
    public <R> Promise<?> thenApply(Function<T, R> onSuccessFunction, Consumer<Throwable> onFailureUserConsumer) {
        // state accepting
        return promiseState.apply(onSuccessFunction, onFailureUserConsumer);
    }

    public <R> Promise<?> thenApply(Function<T, R> onSuccessFunction) {
        return thenApply(onSuccessFunction, thr -> {});
    }

    public Promise<T> thenAccept(Consumer<T> onSuccessUserConsumer) {
        return thenAccept(onSuccessUserConsumer, thr -> {});
    }

    public Promise<T> catchThrowable(Consumer<Throwable> onFailureUserConsumer) {
        return thenAccept(val -> {
        }, onFailureUserConsumer);
    }

    // todo awaits internally, make sure this behavior is right
    public Promise<T> finallyCall(Runnable finallyRunnable) {
        promiseState.finallyCall(finallyRunnable);
        await();
        return this;
    }

    public void await() {
        promiseState.callConsumer();

        try {
            done.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public T get() throws Throwable {
        await();
        return promiseState.get();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setPromiseState(PromiseState<T> promiseState) {
        this.promiseState = promiseState;
    }

    public List<Consumer<T>> getOnSuccessConsumerList() {
        return onSuccessConsumerList;
    }

    public List<Consumer<Throwable>> getOnFailureConsumerList() {
        return onFailureConsumerList;
    }

    public BiConsumer<Consumer<T>, Consumer<Throwable>> getPromiseConsumer() {
        return promiseConsumer;
    }

    public CountDownLatch getDone() {
        return done;
    }

    public Runnable getFinallyCallRunnable() {
        return finallyCallRunnable;
    }

    public void setFinallyCallRunnable(Runnable finallyCallRunnable) {
        this.finallyCallRunnable = finallyCallRunnable;
    }

    public List<Function<T, ?>> getOnSuccessFunctionList() {
        return onSuccessFunctionList;
    }
}
