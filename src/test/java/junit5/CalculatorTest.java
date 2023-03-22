package junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {

	private Calculator calculator;
	private Calculator calculatorNull;
	private static Calculator calculatorStatic;

	@BeforeAll
	public static void beforeAllTests() {
		calculatorStatic = new Calculator();
		System.out.println("@BeforeAll -> beforeAllTests()");

	}

	@AfterAll
	public static void afterAllTests() {
		calculatorStatic = null;
		System.out.println("@AfterAll -> afterAllTests()");

	}

	/*
	 * Se usa para instanciar recursos antes de ejecutar los test
	 */
	@BeforeEach
	public void SetUp() {
		calculator = new Calculator();
		System.out.println("@BeforeEach -> setup()");
	}

	/*
	 * Metodo de limpieza, en terminologia tearDown. destruye los objetos despues de
	 * realizar los test, sirve para liberar recursos.
	 */
	@AfterEach
	public void tearDown() {
		calculator = null;
		System.out.println("@After -> tearDown()");
	}

	@Test
	public void calcularNotNullTest() {
		assertNotNull(calculatorStatic, "Calculator no debe ser null");

		assertNotNull(calculator, "Calculator no debe ser null");
		System.out.println("@Test -> calcularNotNullTest()");
		System.out.println(" --------------------");

	}

	@Test
	public void CalculatorNullTest() {
		assertNull(calculatorNull, "Caulculator debe ser null");
		System.out.println("@Test - > CalculatorNullTest()");
		System.out.println(" --------------------");

	}

	@Test
	public void addAssertTest() {
		// 1. SetUp

		int resultadoEsperado = 30;
		int resultadoActual;
		// 2. Action
		resultadoActual = calculator.add(10, 20);
		// 3. Assert , verifica si el resultado obtenido es igual al resultado calculado
		// en el metodo add()
		assertEquals(resultadoEsperado, resultadoActual);
		System.out.println("@Test ->  addAssertTest() ");

	}

	@Test
	public void subtractAssertTest() {
		// 1. SetUp

		int resultadoEsperado = -10;
		int resultadoActual;
		// 2. Action
		resultadoActual = calculator.subtract(10, 20);
		// 3. Assert , verifica si el resultado obtenido es igual al resultado calculado
		// en el metodo subtract()
		assertEquals(resultadoEsperado, resultadoActual);
		System.out.println("@Test ->  subtractAssertTest() ");

	}
	/*
	 * Forma mas sencilla
	 */

	@Test
	public void add_subTest() {
		assertEquals(30, calculator.add(10, 20));
		assertEquals(10, calculator.subtract(20, 10));
		System.out.println("@Test -> add_subTest()");
	}

	@Test
	public void AssertTypesTest() {
		assertTrue(1 == 1);
		// assertTrue(1==2);
		assertNull(calculatorNull);
		assertNotNull(calculator);

		Calculator cal1 = new Calculator();
		Calculator cal2 = new Calculator();
		Calculator cal3 = cal1;
		assertSame(cal1, cal3);
		// assertSame(cal1, cal2);
		assertNotSame(cal1, cal2);
		// assertNotSame(cal1, cal3);
		System.out.println("@Test AssertTypesTest() ");
		assertEquals("renzo", "renzo");
		System.out.println("@Test assertEquals() ");
		// assertEquals("renzo", "renso","Ha fallado el metodo string");
		// para esperar un rango, se le agrega un tercer parametro ,
		assertEquals(1, 1.4, 0.5);
		// assertEquals(1,1.6, 0.5);

	}

	@Test
	public void addValidInputExpectedTest() {
		assertEquals(11, calculator.add(1, 10));
		System.out.println("@Test addValidInputExpectedTest() ");

	}

	@Test
	public void subtractValidInputValidExpectedTest() {
		assertEquals(10, calculator.subtract(20, 10));
		System.out.println("@Test subtractValidInputValidExpectedTest() ");

	}

	@Test
	public void subtractValidInputValidNegativeExpectedTest() {
		assertEquals(-10, calculator.subtract(20, 30));
		System.out.println("@Test subtractValidInputValidNegativeExpectedTest() ");

	}

	@Test
	public void divideValidInputValidExpectedTest() {
		assertEquals(10, calculator.divide(20, 2));
		System.out.println("@Test divideValidInputValidExpectedTest() ");

	}

	@Test
	public void divideInValidInputValidExpectedTest() {
		// fail("fallo detectado manualmente, no se puede dividir entre cero.");
		// assertEquals(10, calculator.divide(20, 2));
		System.out.println("@Test divideInValidInputValidExpectedTest() ");

	}

	@Test
	public void divideInvalidInputValidTest() {
		assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "error");
		System.out.println("@Test divideInvalidInputValidTest() ");

	}

	@Disabled("Disabled until bug 23")
	@Test
	public void divideInvalid() {
		assertEquals(2, calculator.divide(5, 0));
		System.out.println("@Test divideInvalid() ");

	}

	@DisplayName("Metodo dividir - > Funciona")
	@Test
	public void divideValidInputValidResultExpectedNameTest() {
		assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "error");
		System.out.println("@Test divideValidInputValidResultExpectedNameTest() ");

	}
	// sirve para correr todos los test, si uno falla de todas formas se ejecutan los test restantes.
	@Test
	public void assertAllTest() {
		System.out.println("@Test assertAllTest() ");
		assertAll(() -> assertEquals(30, calculator.add(20, 10)),
				() -> assertEquals(10, calculator.subtract(20, 10)),
				() -> assertEquals(40, calculator.subtract(50, 10)));
	}

	@Nested
	class Addtest {
		

		@Test
		public void addPositiveTest() {
			assertEquals(30, calculator.add(15, 15));
			System.out.println("@Test addPositiveTest() ");
		}
		
		@Test
		public void addNegativeTest() {
			assertEquals(-30, calculator.add(-15, -15));
			System.out.println("@Test addNegativeTest() ");
		}
		@Test
		public void addZeroTest() {
			assertEquals(0, calculator.add(0, 0));
			System.out.println("@Test addNegativeTest() ");
		}
	}
	
		@ParameterizedTest(name = "{index} => a={0} , b ={1} , sum ={2}")
		@MethodSource("addProviderData")

		public void addParameterizedTest(int a, int b, int sum) {
			assertEquals(sum, calculator.add(a, b));

		}

		private static Stream<Arguments> addProviderData() {
			return Stream.of(
					Arguments.of(6, 2, 8), 
					Arguments.of(-6, -2, -8), 
					Arguments.of(6, -2, 4), 
					Arguments.of(6, 2, 8),
					Arguments.of(6, 0, 6));
		}

	@Test
	public void timeOutTest() {
		assertTimeout(Duration.ofMillis(2000), () ->{
			calculator.longTaskOperation();
		});
	}
	
	
}
