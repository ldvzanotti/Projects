import java.util.*;

public class GameMain {

	public static void main (String[] args) {

		//Variable to store number of tries
		int numberOfTries = 3;
		//Instantiates a list to store user input numbers
		List<Integer> inputNumbers = new ArrayList<>();
		//Instantiates a list to store random generated numbers
		List<Integer> randomGeneratedNumbers = new ArrayList<>();
		//Instantiates a Random object
		Random rng = new Random();
		//Instantiates a Scanner object that scans console input
		Scanner scan = new Scanner(System.in);

		//Generation of 3 different random numbers from 0 to 9
		for (int i = 0; i < 3; i++) {
			int rn = rng.nextInt(20);
			//If number is already on the list, generates a new one
			if (randomGeneratedNumbers.contains(rn)) {
				rn = rng.nextInt(20);
			}
			//Adds the number to the list
			randomGeneratedNumbers.add(rn);
		}

		System.out.println("Enter three numbers from 0 to 20 to make a guess:");

		do {
			 try {
				//Variable to store the input
				int guess = scan.nextInt();
				//If typed number is greater than 9, asks for a new number
				if (guess > 20) {
					System.out.println("This number is greater than 20. Type a new number from 0 to 20:");
				}
				//If typed number was already typed, asks for a new number
				else if (inputNumbers.contains(guess)) {
					System.out.println("You already typed this number. Type another number from 0 to 20:");
				}
				//If typed number fits the parameters, adds it to the list
				else {
					inputNumbers.add(guess);
					numberOfTries--;
					if (numberOfTries < 1) System.out.println("Got it!");
					else System.out.println("Got it! Type the next number:");
				}
			//If user inputs a data type other than Integer, throws an exception
			} catch (InputMismatchException ime) {
				System.out.println("You must type NUMBERS! Type a new number from 0 to 20:");
				scan.nextLine();
			}
		} while (numberOfTries > 0);
		System.out.printf("%n-----------------------------------------%n%n");
		System.out.println("Checking for matches...");
		//Sorts the list ascending
		randomGeneratedNumbers.sort(Integer::compareTo);
		//Instantiates a list of guessed numbers, containing the input numbers
		List<Integer> guessedNumbers = new ArrayList<>(inputNumbers);
		//Compares the list with the random numbers, excluding the input numbers that were wrong from the list
		guessedNumbers.retainAll(randomGeneratedNumbers);
		//Switch-case for a custom message based on number of guesses
		switch (guessedNumbers.size()) {
			case (1) -> System.out.println("You got only one number: " + guessedNumbers + ".");
			case (2) -> System.out.println("Almost! You got two numbers: " + guessedNumbers + ".");
			case (3) -> System.out.println("Congratulations! You guessed all the numbers!");
			default -> {
				System.out.println("You didn't guess any number.");
				System.out.println("Your numbers were: " + inputNumbers);
			}
		}
		System.out.println("The numbers were: " + randomGeneratedNumbers);
	}
}
