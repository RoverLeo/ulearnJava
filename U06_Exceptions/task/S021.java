package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S021 {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS021.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS021 {
	Thrower thrower = new Thrower();

	@Test
	public void notContainsDropTable() {
		thrower.throwException("Java лучший язык, C# дно");
	}

	@Test(expected = WrongNameException.class)
	public void contains() {
		thrower.throwException("i am hacker and you drop table rigth now!");
	}

	@Test(expected = WrongNameException.class)
	public void containsCapital() {
		thrower.throwException("stop this DROP TABLE");
	}

	@Test
	public void containsNull() {
		thrower.throwException(null);
	}
}

// Если имя содержит "DROP TABLE"
// Бросить исключение WrongNameException
// в незасимости от регистра
class Thrower {
	public void throwException(String name) {
		if ("drop table".equalsIgnoreCase(name)) {
			throw  new WrongNameException();
		}
	}
}

class WrongNameException extends RuntimeException {

}
