package part01;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * An application class that allows the user to add, search and display images
 * 
 * @author Bryan Durac
 */
public class QUBImage {

// String array of menu options
	static final String options[] = { "Add Image", "Search", "Display All", "Quit" };

// Defines a constant QUIT
	static final int QUIT = options.length;

// A menu title
	static String title = "QUB Image Manager";

// Defines a menu using title & options
	static Menu myMenu = new Menu(title, options);

// Defines a Scanner
	static Scanner input = new Scanner(System.in);

// Defines an instance of ImageManager
	private static ImageManager img;

	public static void main(String[] args) {
		img = initialise();
		int choice;
		do {
			choice = myMenu.getUserChoice(); // displays menu to the user
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		System.out.println("\nGoodbye!");
	}

	// The users choice is processed
	private static void processChoice(int value) {
		switch (value) {
		case 1:
			addAnImage();
			break;
		case 2:
			searchImages();
			break;
		case 3:
			displayAllImages();
			break;
		default:
			System.out.println("Unknown option: " + value + "\n");
		}
	}

	// Ask the user for details of the new image to be added
	private static void addAnImage() {
		try {
			System.out.print("Enter the title of the image: ");
			String title = input.nextLine();
			System.out.print("Enter a description for the image: ");
			String description = input.nextLine();
			System.out.print("Enter the date the image was taken (YYYY-MM-DD): ");
			String date = input.nextLine();
			LocalDate dateTaken = LocalDate.parse(date);
			System.out.print("Enter the thumbnail of the image: ");
			String thumbnail = input.nextLine();
			System.out.println();

			ImageRecord record = new ImageRecord(title, description, ImageType.OTHER, dateTaken, thumbnail);
			img.addImage(record);
		} catch (Exception ex) {
			System.out.println("Error" + ex);
		}
	}

	/**
	 * allows the user to search image records by their id, title, description,
	 * image type or between a specific date range
	 */
	private static void searchImages() {
		try {
			System.out.println("Please choose by which method you would like to search.");
			System.out.print("1. ID, 2. Title, 3, Description, 4. Type, 5. Date Range: ");
			int choice = input.nextInt();
			input.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter ID: ");
				int id = input.nextInt();
				input.nextLine();
				ImageRecord record = img.searchId(id);
				if (record != null) {
					System.out.print("Search Result: " + record);
				} else {
					System.out.println("No image with id: " + id);
				}
				System.out.println();
				break;

			case 2:
				System.out.print("Enter title: ");
				String title = input.nextLine();
				ImageAlbum record1 = img.searchTitle(title);
				if (record1 != null) {
					System.out.print("Search Result: " + record1);
				} else {
					System.out.println("No image with title: " + title);
				}
				System.out.println();
				break;

			case 3:
				System.out.print("Enter description: ");
				String description = input.nextLine();
				ImageAlbum record2 = img.searchDescription(description);
				if (record2 != null) {
					System.out.print("Search Result: " + record2);
				} else {
					System.out.println("No image with title: " + description);
				}
				System.out.println();
				break;

			case 4:
				System.out.print("Enter Type: ");
				String genre = input.nextLine();
				ImageType type = ImageType.valueOf(genre.toUpperCase());
				ImageAlbum record3 = img.searchGenre(type);
				if (record3 != null) {
					System.out.print("Search Result: ");
					for (int i = 0; i < record3.getImageRecords().size(); i++) {
						ImageRecord rec = record3.getImageRecords().get(i);
						System.out.print(rec);
					}
				} else {
					System.out.println("No image with genre: " + genre);
				}
				System.out.println();
				break;

			case 5:
				System.out.print("Enter start date (YYYY-MM-DD): ");
				String start = input.nextLine();
				LocalDate date1 = LocalDate.parse(start);
				System.out.print("Enter end date (YYYY-MM-DD): ");
				String end = input.nextLine();
				LocalDate date2 = LocalDate.parse(end);
				ImageAlbum record4 = img.searchDates(date1, date2);
				if (record4 != null) {
					System.out.print("Search Result: " + record4);
				} else {
					System.out.println("No images between " + date1 + " and " + date2);
				}
				System.out.println();
				break;
			}
		} catch (Exception ex) {
			System.out.println("Error" + ex);
		}
	}

	// Displays all the image records
	private static void displayAllImages() {
		ImageAlbum all = img.getAllImages();
		System.out.println(all);
		System.out.println();
	}

	// Initialises instances of ImageRecord
	private static ImageManager initialise() {
		try {
			img = new ImageManager(); // Instantiate ImageManager

			ImageRecord image1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.",
					ImageType.ASTRONOMY, LocalDate.of(2023, 01, 01), "Andromeda.png");
			ImageRecord image2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.",
					ImageType.ARCHITECTURE, LocalDate.of(2023, 02, 01), "LanyonQUB.png");
			ImageRecord image3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",
					ImageType.SPORT, LocalDate.of(2023, 03, 01), "KermitGolf.png");
			ImageRecord image4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.",
					ImageType.LANDSCAPE, LocalDate.of(2023, 04, 01), "Mournes.png");
			ImageRecord image5 = new ImageRecord("Homer Simpson", "Homer Simpson - A portrait of the man.",
					ImageType.PORTRAIT, LocalDate.of(2023, 03, 01), "Homer.png");

			img.addImage(image1);
			img.addImage(image2);
			img.addImage(image3);
			img.addImage(image4);
			img.addImage(image5);

		} catch (Exception ex) {
			System.out.println("Error" + ex);
		}
		return img;
	}
}