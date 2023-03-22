package junit5;

public class TemperatureCalculator {

	public double toFarenheit(double degree) {
		float valorFloat = new Float(degree);
		if (degree ==0) {
			throw new ArithmeticException("No se puede dividir entre cero.");
		}
		return (valorFloat * 9 / 5) + 32;
	}
	
	public Float toFarenheitFloat(Float degree) {
		Float valorFloat = new Float(degree);
		if (degree ==0) {
			throw new ArithmeticException("No se puede dividir entre cero.");
		}
		return (valorFloat * 9 / 5) + 32;
	}
}
