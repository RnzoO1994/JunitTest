package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class AddTest {
	// se utiliza injectMocks en el objeto del metodo a probar
	@InjectMocks
	private Add add;
	// se usa la etiqueta mock para los objetos que se utilizan en el metodo a
	// probar
	@Mock
	private ValidNumber validNumber;

	@Mock
	private Print print;

	@Captor
	private ArgumentCaptor<Integer> captor  ;
	
	
	@BeforeEach
	public void SetUp() {
		// te aseguras de que este el objeto iniciado y mockeado.
		MockitoAnnotations.initMocks(this);

		System.out.println("@BeforeEach -> setup()");
	}

	@Test
	public void addTest() {
		// ValidNumber validNumber = Mockito.mock(ValidNumber.class);
		// se especifica el valor de retorno de un metodo mockeado
		when(validNumber.check(3)).thenReturn(true);
		// se ejecuta el metodo
		boolean checkNumber = validNumber.check(3);
		//
		assertEquals(true, checkNumber);

		when(validNumber.check("a")).thenReturn(false);
		checkNumber = validNumber.check("a");
		assertEquals(false, checkNumber);

	}

	@Test
	public void addMockExceptionTest() {
		when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero."));
		Exception ex = null;
		try {
			validNumber.checkZero(0);
		} catch (ArithmeticException e) {

			ex = e;
			// TODO: handle exception
		}
		System.out.println(ex);
		assertNotNull(ex);
	}

	@Test
	public void addRealMethodTest() {
		when(validNumber.check(3)).thenCallRealMethod();
		assertEquals(true, validNumber.check(3));

		when(validNumber.check("3")).thenCallRealMethod();
		assertEquals(false, validNumber.check("3"));
	}

	@Test
	public void addDoubleToIntAnswer() {
		Answer<Integer> answer = new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
				return 8;

			}
		};
		when(validNumber.doubleToInt(8.7)).thenAnswer(answer);
		assertEquals(16, add.addInt(8.7));
		System.out.println("resultado de doubleToInt " + 7.7 + " es :" + add.addInt(7.7));
	}

	@Test
	public void patternTest() {
		// Arrange
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);
		// act
		int result = add.add(4, 5);
		// assert
		assertEquals(9, result);
	}

	@Test
	public void patternBDDTest() {
		// given

		given(validNumber.check(4)).willReturn(true);

		given(validNumber.check(5)).willReturn(true);
		// when
		int result = add.add(4, 5);
		// then
		assertEquals(9, result);
	}

	@Test
	public void argumentMatcherTest() {

		// given
		given(validNumber.check(anyInt())).willReturn(true);
		// when
		int result = add.add(4, 5);
		// then
		assertEquals(9, result);
	}

	@Test
	public void addPrintTest() {
		// Given
		given(validNumber.check(4)).willReturn(true);
		given(validNumber.check(5)).willReturn(true);
		// when
		add.addPrint(4, 5);
		//then
		// aqui se verifica que el parametro 4 se envia una sola ves, funciona cuando en el given se envia una sola vez el 4
		 verify(validNumber).check(4);
		 // el verify con times, indica la cantidad  de veces que se esta enviando un parametro, en este caso es el 4
		 // sirve cuando se envia en el metodo addPrint dos veces el 4, si son parametros distintos ya no funciona.
		//verify(validNumber, times(2)).check(4);
		 // se verifica que nunca se haya usado un parametro determinado en la ejecucion de algun metodo.
		 verify(validNumber, never()).check(99);
		 verify(validNumber, atLeast(1)).check(4);
		 verify(validNumber, atMost(3)).check(4);
		// add.addPrint(4,4);
		 verify(print).showMessage(9);
		 verify(print, never()).showError();
	}
	
	@Test
	public void captorTest() {
		// Given
		given(validNumber.check(4)).willReturn(true);
		given(validNumber.check(5)).willReturn(true);
		// when
		add.addPrint(4, 5);
		//then
		 verify(print).showMessage(captor.capture());
		 assertEquals(captor.getValue().intValue(), 9);
		 
	}
	@Spy
	List<String> spyList = new ArrayList<>();
	
	@Mock
	List<String> mockList = new ArrayList<>();
	
	@Test
	public void spyTest() {
		spyList.add("1");
		spyList.add("2");
		verify(spyList).add("1");
		verify(spyList).add("2");
		assertEquals(2,spyList.size());
		
	}
	@Test
	public void mockTest() {
		mockList.add("1");
		mockList.add("2");
		verify(mockList).add("1");
		verify(mockList).add("2");
		// para que no de error, se inicializa el tamaño con la sentencia given
		given(mockList.size( )).willReturn(2);

		assertEquals(2,mockList.size());

		
	}
}
