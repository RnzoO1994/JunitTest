package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AddCreateMockTest {

	private Add add;
	 
	private ValidNumber validNumber;
	
	@AfterEach
	public void tearDown() {
		validNumber = null;
		System.out.println("@After -> tearDown()");
	}
	@BeforeEach
	public void SetUp() {
		//
		validNumber = Mockito.mock(ValidNumber.class);
		add = new Add(validNumber);
 
		System.out.println("@BeforeEach -> setup()");
	}

	
	@Test
	public void addTest() {
		add.add(3, 2);
		Mockito.verify(validNumber).check(3);
		Mockito.verify(validNumber).check(2);
		// no pasa la prueba porque no a sido llamado al momento de
		//invocar el metodo add.add(3,2)
		//Mockito.verify(validNumber).check(5);
	}

}
