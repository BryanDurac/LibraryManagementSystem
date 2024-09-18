package part01;

import java.util.Scanner;

public class Menu {
	private String items[]; // array of strings representing user options
	private String title; // menu title
	private Scanner input; // Scanner class to read user input

	/**
	 * Constructor to build menu object
	 * 
	 * @param title - the menu title
	 * @param data  - the options for user selection
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	// A method to display the menu
	private void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}

		System.out.println();
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	/**
	 * A method that displays the menu and asks the user for input
	 * 
	 * @return
	 */
	public int getUserChoice() {
		display();
		int value = 0;
		do {
			try {
				System.out.print("Enter Selection: ");
				value = input.nextInt();
				input.nextLine();
				break;
			} catch (Exception ex) {
				input.nextLine();
			}
		} while (true);
		return value;
	}
}