package tuto.prog_principles;

public class recursivity {

	public static void main(String[] args) {
		int numberToCalc = 10;
		int result = factorial(numberToCalc);
		System.out.println("the factorial of " + numberToCalc + " is " + result);

	}

	// ----- Functions ----- //
	public static int factorial(int number) {
		if (number == 1)
			return 1;
		else
			return number * factorial(number - 1);
	}

}