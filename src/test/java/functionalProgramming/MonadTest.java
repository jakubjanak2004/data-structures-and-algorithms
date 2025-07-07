package functionalProgramming;

import dsa.functionalProgramming.Wrappers.Monad;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MonadTest {
    @Test
    public void ofThrowsNullPointerExceptionWhenNullIsPassed() {
        assertThrows(NullPointerException.class, () -> Monad.of(null));
    }

    @Test
    public void ofNullableConstructsEmptyMaybeWhenNullIsPassed() {
        assertEquals(Monad.empty(), Monad.ofNullable(null));
    }

    @Test
    public void mapDoesNotThrowNullPointerException() {
        Monad<String> monad = Monad.empty();
        Monad<String> newMaybe = monad.map(String::toUpperCase);
        assertNull(newMaybe.get());
    }

    @Test
    public void mapReturnsNewOptionalWithChangedContents() {
        Monad<String> monad = Monad.of("Test");
        Monad<String> newMaybe = monad.map(String::toUpperCase);
        assertEquals("TEST", newMaybe.get());
    }

    @Test
    public void flatMapDoesNotThrowNullPointerException() {
        Monad<String> monad = Monad.empty();
        Monad<String> newMaybe = monad.flatMap(value -> Monad.of(value + " changed"));
        assertNull(newMaybe.get());
    }

    @Test
    public void flatMapReturnsNewOptionalWithChangedContents() {
        Monad<String> monad = Monad.of("Test");
        Monad<String> newMaybe = monad.flatMap(value -> Monad.of(value + " changed"));
        assertEquals("Test changed", newMaybe.get());
    }

    @Test
    public void ifPresentDoesNotCallConsumerOnNullValue() {
        Monad<String> monad = Monad.empty();
        Consumer<String> mockedConsumer = mock(Consumer.class);
        monad.ifPresent(mockedConsumer);
        verify(mockedConsumer, never()).accept(any());
    }

    @Test
    public void ifPresentCallsConsumerOnNotNullValue() {
        Monad<String> monad = Monad.of("Test");
        Consumer<String> mockedConsumer = mock(Consumer.class);
        monad.ifPresent(mockedConsumer);
        verify(mockedConsumer).accept("Test");
    }

    @Test
    public void orElseReturnsOtherWhenValueIsNull() {
        Monad<String> monad = Monad.empty();
        assertEquals("other", monad.orElse("other"));
    }

    @Test
    public void orElseReturnsValueWhenNonNull() {
        Monad<String> monad = Monad.of("Test");
        assertEquals("Test", monad.orElse("other"));
    }

    @Test
    public void orElseThrowThrowsExceptionWhenValueIsNull() {
        Monad<String> monad = Monad.empty();
        assertThrows(RuntimeException.class, () -> monad.orElseThrow(RuntimeException::new));
    }

    @Test
    public void orElseThrowReturnsValueWhenValueIsNotNull() throws Throwable {
        Monad<String> monad = Monad.of("Test");
        assertEquals("Test", monad.orElseThrow(RuntimeException::new));
    }
}
