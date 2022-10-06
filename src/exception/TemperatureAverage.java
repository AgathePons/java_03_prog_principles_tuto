package exception;

import java.util.ArrayList;
import java.util.List;

public class TemperatureAverage {

	public static void main(String[] args) {
		List<Integer> temperaturesList = new ArrayList<Integer>();

		try {
			for (String stringTemperature : args) {
				int temperature = Integer.parseInt(stringTemperature);
				temperaturesList.add(temperature);
			}

			Integer averageTemperature = SimpleMaths.calculateAverage(temperaturesList);
			System.out.println("The average temperature is " + averageTemperature);
		} catch (NumberFormatException event) {
			System.out.println("All arguments should be provided as numbers");
			System.exit(-1);
		} catch (ArithmeticException event) {
			System.out.println("At least 1 temperature should be provided");
			System.exit(-1);
		}

	}

}
