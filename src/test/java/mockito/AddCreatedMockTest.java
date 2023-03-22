package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AddCreatedMockTest {
	// se utiliza injectMocks en el objeto del metodo a probar
	@InjectMocks
	private Add add;
	// se usa la etiqueta mock para los objetos que se utilizan en el metodo a probar
	@Mock
	private ValidNumber validNumber;
	
	@BeforeEach
	public void SetUp() {
		// te aseguras de que este el objeto iniciado y mockeado.
		MockitoAnnotations.initMocks(this);
		System.out.println("@BeforeEach -> setup()"); 
	}
	@Test
	public void addTest() {
		add.add(3, 2);
		Mockito.verify(validNumber).check(3);
 
	}


}
