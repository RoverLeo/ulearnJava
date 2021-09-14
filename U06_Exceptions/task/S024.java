package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.lang.module.FindException;
import java.util.function.Function;

public class S024 {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS024.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS024 {
	@Test(expected = MyRuntimeException.class)
	public void MyRuntimeException() {
		Function<String, Integer> methodThrowsException =
				(String t) -> {
					throw new RuntimeException();
		};
		ExceptionTransformator transformator = new ExceptionTransformator();
		transformator.transormate(methodThrowsException);
	}

	@Test(expected = MySecurityException.class)
	public void MySecurityException() {
		Function<String, Integer> methodThrowsException =
				(String t) -> {
						throw new SecurityException();
		};
		ExceptionTransformator transformator = new ExceptionTransformator();
		transformator.transormate(methodThrowsException);
	}

	@Test(expected = IllegalArgumentException.class)
	public void MyIllegalArgumentException() {
		Function<String, Integer> methodThrowsException =
				(String t) -> {
				throw new IllegalArgumentException();
		};

		ExceptionTransformator transformator = new ExceptionTransformator();
		transformator.transormate(methodThrowsException);
	}

	@Test(expected = MyFindException.class)
	public void MyFindException() {
		Function<String, Integer> methodThrowsException =
				(String t) -> {
				throw new FindException();
		};

		ExceptionTransformator transformator = new ExceptionTransformator();
		transformator.transormate(methodThrowsException);
	}
}

// Заверни exception при вызове функции в другие
// Все ошибки реализованы
// Exception -> MyException
class ExceptionTransformator {
	public void transormate(Function methodThrowsException) {
		try {
			methodThrowsException.apply(null);
		} catch (SecurityException e) {
			throw new MySecurityException(e);
		} catch (IllegalArgumentException e) {
			throw new MyIllegalArgumentException(e);
		} catch (FindException e) {
			throw new MyFindException(e);
		} catch (MyRuntimeException e) {
			throw new RuntimeException(e);
		}
	}
}

class MyRuntimeException extends RuntimeException {
	public MyRuntimeException(Throwable cause) {
		super(cause);
	}
}

class MySecurityException extends SecurityException {
	public MySecurityException(Throwable cause) {
		super(cause);
	}
}

class MyIllegalArgumentException extends IllegalArgumentException{
	public MyIllegalArgumentException(Throwable cause) {
		super(cause);
	}
}

class MyFindException extends FindException {
	public MyFindException(Throwable cause) {
		super(cause);
	}
}