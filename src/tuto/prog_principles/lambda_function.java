package tuto.prog_principles;

public class lambda_function {

	// Lambda expression - functionnal interfaces
	public interface Student {
		void giveSubject();
	}

	public interface Pokemon {
		void call(String name, String cry);
	}

	public interface BagOfCandies {
		int numberOfCandiesIHave(int firstTypeNumber, String firstTypeName, int secondTypeNumber,
				String secondTypeName);
	}

	public static void main(String[] args) {
		// Lambda expression - usecase
		Student myStudent = () -> {
			System.out.println("I study art");
		};

		myStudent.giveSubject();

		Pokemon pikachu = (name, cry) -> {
			System.out.println("The " + name + " says: " + cry);
		};

		pikachu.call("Pikachu", "pika pika pikachuuu");

		Pokemon diglett = (name, cry) -> {
			System.out.println("A " + name + " appears: " + cry);
		};

		diglett.call("Diglett", "Digi Digi digiglett!!");

		BagOfCandies myBag = (firstTypeNumber, firstTypeName, secondTypeNumber, secondTypeName) -> {
			System.out.println("I have " + firstTypeNumber + " " + firstTypeName);
			System.out.println("I have " + secondTypeNumber + " " + secondTypeName);
			int numberOfCandies = firstTypeNumber + secondTypeNumber;
			return numberOfCandies;
		};

		int numberOfCandiesIHave = myBag.numberOfCandiesIHave(5, "chewing-gums", 8, "lollipops");
		System.out.println("In total, I have " + numberOfCandiesIHave + " candies!");

		// Anonymous class example
		class Bird {
			void fly() {
				System.out.println("Just flying");
			}
		}

		Bird yourBird = new Bird();

		Bird myBird = new Bird() {
			@Override
			void fly() {
				System.out.println("Flyyyyyyyyy");
			}
		};

		yourBird.fly();
		myBird.fly();

	}

}
