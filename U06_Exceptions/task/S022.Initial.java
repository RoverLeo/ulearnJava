package U06_Exceptions.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.function.Function;

public class JUlearn {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS022.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS022 {
	ExceptionsStoper stoper = new ExceptionsStoper();

	@Test
	public void stopIllegalArgumentException() {
		Function functionThrowException = o -> {
			throw new IllegalArgumentException();
		};
		int res = stoper.stop(functionThrowException);
		Assert.assertEquals(res, 5);
	}

	@Test
	public void stopRuntimeException() {
		Function functionThrowException = o -> {
			throw new RuntimeException();
		};
		int res = stoper.stop(functionThrowException);
		Assert.assertEquals(res, 3);
	}

	@Test(expected = OutOfMemoryError.class)
	public void notStopThrowable() {
		Function functionThrowException = o -> {
			throw new OutOfMemoryError();
		};
		stoper.stop(functionThrowException);
	}
}

// Здесь 2 ошибки
// Найдите её
// Потеряна важная информация (здесь bad practise)
class ExceptionsStoper {
	public int stop(Function methodThrowsException) {
		try {
			methodThrowsException.apply(null);
		} catch (RuntimeException e) {
			return 3;
		} catch (IllegalArgumentException e) {
			return 5;
		} catch (Throwable e) {
			return -1;
		}
		return 0;
	}
}

