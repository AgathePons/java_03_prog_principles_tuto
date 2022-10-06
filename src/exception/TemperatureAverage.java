package exception;

import java.util.ArrayList;
import java.util.List;

public class TemperatureAverage {

	public static void main(String[] args) {
		List<Integer> temperaturesList = new ArrayList<Integer>();

		for (String stringTemperature : args) {
			int temperature = Integer.parseInt(stringTemperature);
			temperaturesList.add(temperature);
		}

		if (temperaturesList.size() == 0) {
			System.out.println("The list of temperatures is empty!");
		} else {
			Integer averageTemperature = SimpleMaths.calculateAverage(temperaturesList);
			System.out.println("The average temperature is " + averageTemperature);
		}
	}

}
