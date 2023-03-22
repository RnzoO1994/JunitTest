package mockito;

public class Add {
	private ValidNumber validNumber;
	private Print print;

	public Add(ValidNumber valid) {
		this.validNumber = valid;

	}
	public Add(ValidNumber validnumber, Print print) {
		this.validNumber = validnumber;
		this.print=print;
	}
	public int add(Object a, Object b) {
		if (validNumber.check(a) && validNumber.check(b)) {
			return (Integer) a + (Integer) b;
		}
		return 0;
	}

	public int addInt(Object a) {
		return validNumber.doubleToInt(a) + validNumber.doubleToInt(a);
	}

	public void addPrint(Object a, Object b) {
		if (validNumber.check(a) && validNumber.check(b)) {
	 
			int result = (Integer) a + (Integer) b;
			System.out.println("RESULT "  + result);
			print.showMessage(result);
		} else {
			print.showError();
		}
	}
}
