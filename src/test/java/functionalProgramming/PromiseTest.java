package functionalProgramming;

import dsa.functionalProgramming.Promise.Promise;
import org.junit.Test;

public class PromiseTest {

    private static class PromiseObject {
        private int counter = 0;

        private void incrementCounter() {
            counter++;
        }

        private int getCounter() {
            return counter;
        }

        @Override
        public String toString() {
            return "{counter: " + getCounter() + "}";
        }
    }

    private static String objectToStringAsync(Object o) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return o + " <- was created by async method";
    }

//    @Test
//    public void chainedThenReturnsPromise() {
//        Promise<PromiseObject> promise = new Promise<>((onSuccess, onFailure) -> {
//            PromiseObject promiseObject = new PromiseObject();
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            promiseObject.incrementCounter();
//            onSuccess.accept(promiseObject);
//        });
//
//        promise
//            .thenAccept(promiseObject -> System.out.println("Promise object loaded successfully: " + promiseObject))
//            .thenApply(promiseObject -> objectToStringAsync(promiseObject))
//            .thenAccept(System.out::println)
//            .await();
//    }

    @Test
    public void promiseReturnsValueOnThen() {
        Promise<String> promise = new Promise<>((onSuccess, onFailure) -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            onSuccess.accept("Testing Promise");
        });

        promise
            .thenAccept(System.out::println)
            .thenAccept(System.out::println)
            .finallyCall(() -> System.out.println("Finally called, ending the promise lifecycle"));
    }

    @Test
    public void getReturnsTheAsynchronousValue() throws Throwable {
        Promise<String> promise = new Promise<>((onSuccess, onFailure) -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            onSuccess.accept("Testing Promise");
        });

        System.out.println(promise.get());
    }

//    @Test
//    public void promiseCallsCatchThrowableWhenThrowableRaised() {
//        Promise<String> promise = new Promise<>((onSuccess, onFailure) -> {
//            onFailure.accept(new Exception("Promise raised an exception"));
//        });
//
//        promise
//                .thenAccept(System.out::println)
//                .catchThrowable(System.err::println)
//                .finallyCall(() -> System.out.println("Finally called, ending the promise lifecycle"));
//    }
}
