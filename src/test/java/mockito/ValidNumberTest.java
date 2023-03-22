package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNumberTest {

	private ValidNumber validNumber;
	@AfterEach
	public void tearDown() {
		validNumber = null;
		System.out.println("@After -> tearDown()");
	}
	@BeforeEach
	public void SetUp() {
		validNumber = new ValidNumber();
		System.out.println("@BeforeEach -> setup()");
	}

	@Test
	public void checkTest() {
		assertEquals(true, validNumber.check(5));
	}
	
	@Test
	public void checkNegativeTest() {
		assertEquals(false, validNumber.check(-5));
	}
	
	@Test
	public void checkStringTest() {
		assertEquals(false, validNumber.check("r"));
	}
	
	@Test
	public void checkZeroTest() {
		assertEquals(true, validNumber.checkZero(-57));
	}
	
	@Test
	public void checkZeroStringTest() {
		assertEquals(false, validNumber.checkZero("5"));
	}
	@Test
	public void checkZero0StringTest() {
		assertThrows(ArithmeticException.class, ()-> validNumber.checkZero(0));
	}
	
	@Test
	public void doubleToIntTest () {
		assertEquals(0, validNumber.doubleToInt("9.999"));
	}
	
}
