package dsa.functionalProgramming;

import java.util.function.Function;

public interface Endofunctor <T>{
    <R> Endofunctor<R> map(Function<? super T, ? extends R> f);
}
