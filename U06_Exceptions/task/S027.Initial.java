package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUlearn {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS027.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS027 {

	ThrowerException throwerException = new ThrowerException();

	@Test(expected = Exception.class)
	public void testSneyke() {
		throwerException.thisThrowException();
	}

}

// java не может
// lombok поможет
class ThrowerException {

	void thisThrowException() {
		throw new Exception();
	}
}

