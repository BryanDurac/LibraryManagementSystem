package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryService {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Loan> loans = new ArrayList<Loan>();
	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<User> users = new ArrayList<User>();
	private static final int MAX_BOOK_RENEWS = 3;
	private static final int MAX_MULTIMEDIA_RENEWS = 2;
	static String file1 = "/Users/bryan/Downloads/USERS.csv";
	static String file2 = "/Users/bryan/Downloads/ITEMS.csv";
	static String file3 = "/Users/bryan/Downloads/LOANS.csv";

	public static void main(String[] args) {
		users = loadUsersFromCSV(file1);
		items = loadItemsFromCSV(file2);
		loans = loadLoansFromCSV(file3);

		// B00103171 433849131
		// 514284220

		try {
			int choice;
			do {
				System.out.println("Library Management System");
				System.out.println("1. Issue Loan");
				System.out.println("2. Renew Loan");
				System.out.println("3. Return Item on Loan");
				System.out.println("4. View Loans");
				System.out.println("5. Search Loans by Barcode");
				System.out.println("6. Generate Loan Report");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				choice = input.nextInt();
				input.nextLine();
				System.out.println();
				processChoice(choice);
			} while (choice != 7);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	private static void processChoice(int value) {
		switch (value) {
		case 1:
			issueLoan();
			break;
		case 2:
			renewLoan();
			break;
		case 3:
			returnItemOnLoan();
			break;
		case 4:
			viewAllItemsOnLoan();
			break;
		case 5:
			System.out.print("Enter Item Barcode: ");
			String itemBarcode = input.nextLine();
			searchLoanItemByBarcode(itemBarcode);
			break;
		case 6:
			report();
			break;
		case 7:
			exit();
			break;
		default:
			System.out.println("Unknown option: " + value + "\n");
		}
	}

	private static void issueLoan() {
		System.out.print("Enter User ID: ");
		String userID = input.nextLine();

		if (!userExists(userID)) {
			System.out.println("User with ID " + userID + " does not exist.\n");
			return;
		}

		System.out.print("Enter Item Barcode: ");
		String itemBarcode = input.nextLine();

		if (!itemExists(itemBarcode)) {
			System.out.println("Item with barcode " + itemBarcode + " does not exist.\n");
			return;
		}

		if (loanExists(itemBarcode)) {
			System.out.println("Item with barcode " + itemBarcode + " already has an active loan.\n");
			return;
		}

		Item item = getItemByBarcode(itemBarcode);

		Loan loantemp = new Loan(itemBarcode, userID, LocalDate.now(), null, 0);
		LocalDate returnDate = loantemp.setIssueDueDate(item);

		Loan loan = new Loan(itemBarcode, userID, LocalDate.now(), returnDate, 0);

		loans.add(loan);
		System.out.println("Loan successfully issued.\n");
	}

	private static void renewLoan() {
		System.out.print("Enter Item Barcode: ");
		String itemBarcode = input.nextLine();

		Loan loan = getLoanItemByBarcode(itemBarcode);
		if (loan == null) {
			System.out.println("Item with barcode " + itemBarcode + " does not have an active loan.\n");
			return;
		}

		Item item = getItemByBarcode(itemBarcode);
		renewItem(item, loan);
	}

	private static void renewItem(Item item, Loan loan) {
		int renewals = loan.getNumberOfRenewals();
		if (item.getItemType().equalsIgnoreCase("Book")) {
			if (renewals >= MAX_BOOK_RENEWS) {
				System.out.println("Maximum renewals reached. Cannot renew item.\n");
				return;
			}
			loan.setDueDate(LocalDate.now().plusWeeks(2));
			System.out.println("Loan renewed successfully.\n");
		} else if (item.getItemType().equalsIgnoreCase("Multimedia")) {
			if (renewals >= MAX_MULTIMEDIA_RENEWS) {
				System.out.println("Maximum renewals reached. Cannot renew item.\n");
				return;
			}
			loan.setDueDate(LocalDate.now().plusWeeks(1));
			System.out.println("Loan renewed successfully.\n");
		}
		loan.setNumberOfRenewals(renewals + 1);
	}

	private static void returnItemOnLoan() {
		System.out.print("Enter Item Barcode: ");
		String itemBarcode = input.nextLine();
		returnItem(itemBarcode);
	}

	private static void returnItem(String itemBarcode) {
		if (loanExists(itemBarcode)) {
			Loan loan = getLoanItemByBarcode(itemBarcode);

			LocalDate returnDate = LocalDate.now();
			LocalDate dueDate = loan.getDueDate();
			if (returnDate.isAfter(dueDate)) {
				System.out.println("Loan is overdue. Please contact the Library.");
			}

			loans.remove(loan);
			writeLoansToCSV(loans, file3);
			System.out.println("Loan item successfully returned.\n");
		} else {
			System.out.println("Item with barcode " + itemBarcode + " does not have an active loan.\n");
		}
	}

	private static void viewAllItemsOnLoan() {
		if (loans.size() == 0) {
			System.out.println("No items currently on loan.\n");
		}
		for (Loan loan : loans) {
			System.out.println(loan);
		}
	}

	private static void searchLoanItemByBarcode(String itemBarcode) {
		boolean loanFound = false;
		for (Loan loan : loans) {
			if (loan.getItemBarcode().equals(itemBarcode)) {
				System.out.println(loan);
				loanFound = true;
				break;
			}
		}
		if (!loanFound) {
			System.out.println("Item with " + itemBarcode + " does not have an active loan.\n");
		}
	}

	private static void report() {
		int bookCount = 0;
		int multimediaCount = 0;
		int count = 0;
		for (Loan loan : loans) {
			Item item = getItemByBarcode(loan.getItemBarcode());
			if (item.getItemType().equalsIgnoreCase("Book")) {
				bookCount++;
			} else {
				multimediaCount++;
			}
			int renewals = loan.getNumberOfRenewals();
			if (renewals > 1) {
				count++;
			}
		}
		int size = loans.size();
		double total = ((double) count / size) * 100;

		System.out.println("Bryan Durac Library");
		System.out.println("Total books on loan: " + bookCount);
		System.out.println("Total multimedia on loan: " + multimediaCount);
		System.out.printf("Percentage of loan items having been renewed more than once: %.2f%%\n\n", total);
	}

	private static Item getItemByBarcode(String itemBarcode) {
		for (Item item : items) {
			if (item.getItemBarcode().equals(itemBarcode)) {
				return item;
			}
		}
		return null;
	}

	private static Loan getLoanItemByBarcode(String itemBarcode) {
		for (Loan loan : loans) {
			if (loan.getItemBarcode().equals(itemBarcode)) {
				return loan;
			}
		}
		return null;
	}

	private static boolean userExists(String userID) {
		for (User user : users) {
			if (user.getUserID().equals(userID)) {
				return true;
			}
		}
		return false;
	}

	private static boolean itemExists(String itemBarcode) {
		for (Item item : items) {
			if (item.getItemBarcode().equalsIgnoreCase(itemBarcode)) {
				return true;
			}
		}
		return false;
	}

	private static boolean loanExists(String itemBarcode) {
		for (Loan loan : loans) {
			if (loan.getItemBarcode().equalsIgnoreCase(itemBarcode)) {
				return true;
			}
		}
		return false;
	}

	private static void exit() {
		System.out.println("Goodbye!");
		writeLoansToCSV(loans, file3);
		System.exit(0);
	}

	private static void writeLoansToCSV(ArrayList<Loan> loans, String file3) {
		try (FileWriter writer = new FileWriter(file3)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			for (Loan loan : loans) {
				String loanData = loan.getItemBarcode() + "," + loan.getUserID() + ","
						+ loan.getIssueDate().format(formatter) + "," + loan.getDueDate().format(formatter) + ","
						+ loan.getNumberOfRenewals();
				writer.write(loanData + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<User> loadUsersFromCSV(String file1) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] userData = line.split(",");
				if (userData.length < 4) {
					System.out.println("Invalid user data format in CSV file.");
					continue;
				}
				users.add(new User(userData[0], userData[1], userData[2], userData[3]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	private static ArrayList<Item> loadItemsFromCSV(String file2) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] itemData = line.split(",");
				if (itemData.length < 5) {
					System.out.println("Invalid item data format in CSV file.");
					continue;
				}
				items.add(new Item(itemData[0], itemData[1], itemData[2], itemData[3], itemData[4], itemData[5]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	private static ArrayList<Loan> loadLoansFromCSV(String file3) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file3))) {
			String line;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			while ((line = reader.readLine()) != null) {
				String[] loanData = line.split(",");
				if (loanData.length < 5) {
					System.out.println("Invalid loan data format: " + line);
					continue;
				}
				String itemBarcode = loanData[0];
				String userID = loanData[1];
				LocalDate issueDate = LocalDate.parse(loanData[2], formatter);
				LocalDate dueDate = LocalDate.parse(loanData[3], formatter);
				int numberOfRenewals = Integer.parseInt(loanData[4]);
				loans.add(new Loan(itemBarcode, userID, issueDate, dueDate, numberOfRenewals));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loans;
	}

}