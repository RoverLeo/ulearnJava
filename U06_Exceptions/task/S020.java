package U06_Exceptions.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S020 {
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(UnitTestS020.class); // имя класса, который хотим тестировать
		if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
			TextListener listener = new TextListener(new RealSystem());
			listener.testRunFinished(result);
		}
		System.exit(result.wasSuccessful() ? 0 : 1);
	}
}

public class UnitTestS020 {
	RuntimeThrower thrower = new RuntimeThrower();

	@Test
	public void allIsOk() {
		thrower.throwException(new int[]{2, 3, 1, 0, 4, 168});
	}

	@Test
	public void existNegativNum() {
		try {
			thrower.throwException(new int[]{1, 2, 3, -4, 5});
		} catch (Exception e) {
			Assert.assertEquals("-4" , e.getMessage());
		}
	}

	@Test
	public void emptyArray() {
		thrower.throwException(new int[]{});
	}

	@Test
	public void existManyNegativNums() {
		try {
			thrower.throwException(new int[]{-1, -67, -30, -32, 234, -432, -4, -23, -431});
		} catch (Exception e) {
			Assert.assertEquals("-1" , e.getMessage());
		}
	}
}

// Бросить исключение RuntimeException
// С message с наименьшим числом меньшим 0 в массиве
// иначе ничего не возвращать
class RuntimeThrower {
	public void throwException(int[] nums) {
		if (nums == null || nums.length == 0){
			return;
		}
		int min = nums[0];
		for (int num : nums){
			if (num < min){
				min = num;
			}
		}

		if (min < 0) {
			throw new RuntimeException(Integer.toString(min));
		}
	}
}
