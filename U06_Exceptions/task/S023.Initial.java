package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUlearn {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS023.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS023 {
	CorrectSignature correctSignature = new CorrectSignature();

	@Test(expected = CorrectSignature.MyException.class)
	public void throwMyException() {
		correctSignature.throwException();
	}
}

class CorrectSignature {
	public void throwException() {
		throw new CorrectSignature.MyException();
	}

	class MyException extends Exception {

	}
}

