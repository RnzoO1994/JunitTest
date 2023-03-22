package junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TemperatureCalculatorTest {
	private TemperatureCalculator temperatureCalculator;
	

	@AfterEach
	public void tearDown() {
		temperatureCalculator = null;
		System.out.println("@After -> tearDown()");
	}
	@BeforeEach
	public void SetUp() {
		temperatureCalculator = new TemperatureCalculator();
		System.out.println("@BeforeEach -> setup()");
	}

	@ParameterizedTest(name = "{index} => result={0}, a ={1}")
	@MethodSource("addProviderData")

	public void toFarenheitParameterizedTest(double a, double result) {
		assertEquals(result, temperatureCalculator.toFarenheit(a), 0.01);
		//assertNull(temperatureCalculator);
	}

	private static Stream<Arguments> addProviderData() {
		return Stream.of(
				Arguments.of(32, 89.6), 
				Arguments.of(50, 122), 
				Arguments.of(113, 235.4), 
				Arguments.of(212, 413.6), 
				Arguments.of(6, 42.8));
	}
	
	@ParameterizedTest(name = "{index} => a={0},  result ={1}")
	@MethodSource("addProviderData1")
	public void toFarenheitFloatParameterizedTest(Float   a, Float result ) {
		assertEquals(result, temperatureCalculator.toFarenheit(a), 0.01);
		//assertNull(temperatureCalculator);
	}

	private static Stream<Arguments> addProviderData1() {
		return Stream.of(
				//Arguments.of(32, 89.6f),
				Arguments.of(Float.intBitsToFloat(Float.floatToIntBits((89.6f))), Float.intBitsToFloat(Float.floatToIntBits(193.27f))),
				Arguments.of(Float.intBitsToFloat(Float.floatToIntBits((122f))), Float.intBitsToFloat(Float.floatToIntBits(251.6f))),
				Arguments.of(Float.intBitsToFloat(Float.floatToIntBits((235.4f))), Float.intBitsToFloat(Float.floatToIntBits(455.71f))),
				Arguments.of(Float.intBitsToFloat(Float.floatToIntBits((413.6f))), Float.intBitsToFloat(Float.floatToIntBits(776.48f)))
				); 
	}
	@Test
	public void toFarenheitTest() {
		assertEquals(-9.4, temperatureCalculator.toFarenheit(-23),0.01);
	}
}
