package tuto.prog_principles;

public class recursivity {

	public static Categories categories;

	public static int countBooks(Categories categories) {
		int counter = 0;
		for (Category category : categories) {
			counter += category.numberOfBooks;
			counter += countBooks(category.subCategories);
		}
		return counter;
	}

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