package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import console.Console;

/**
 * An application class that allows the user to add, search and display images
 * Uses console as display
 * 
 * @author Bryan Durac
 */
public class QUBMediaImages {

	private static ImageManager img; // Defines an instance of ImageManager
	private static Console con; // Defines an instance of Console

	// Initialises instances of ImageRecord
	private static ImageManager initialise() {
		ImageManager img1 = new ImageManager();

		try {
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

			img1.addImage(image1);
			img1.addImage(image2);
			img1.addImage(image3);
			img1.addImage(image4);
			img1.addImage(image5);
			return img1;

		} catch (Exception ex) {
			con.println(ex);
		}

		return img1;
	}

	/**
	 * Used to diplay the image to the console
	 * 
	 * @param name
	 * @return
	 */
	private static ImageIcon getImage(String name) {
		String userdir = System.getProperty("user.dir");
		String path = userdir + "/Images/" + name;
		ImageIcon img = new ImageIcon(path);
		return img;
	}

	// Constructing the console window
	private static Console initConsole() {
		con = new Console(true);
		con.setSize(1200, 1000);
		con.setVisible(true);

		// set background colour for console
		con.setBgColour(Color.LIGHT_GRAY);
		// set font for console
		Font ft = new Font("Consolas", Font.BOLD, 20);
		con.setFont(ft);
		// set text colour for console
		con.setColour(Color.BLACK);
		con.println(ft);
		return con;
	}

	public static void main(String[] args) {
		img = initialise();
		con = initConsole();
		int choice;

		// displays the menu and ask user for selection
		do {
			con.println("QUB Image Manager");
			con.println("+++++++++++++++++");
			con.println("1. Add Image");
			con.println("2. Search");
			con.println("3. Display All");
			con.println("4. Quit");
			con.println();
			con.print("Enter Selection: ");
			String strChoice = con.readLn();
			choice = Integer.parseInt(strChoice);
			// displays menu to the user
			if (choice != 4) {
				processChoice(choice);
			}
		} while (choice != 4);
		con.println("\nGoodbye!");
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
			con.println("Unknown option: " + value + "\n");
		}
	}

	// displays all images to the console alongside their details
	private static void displayAllImages() {
		ImageAlbum allImages = img.getAllImages();
		ArrayList<ImageRecord> imageList = allImages.getImageRecords();
		for (int index = 0; index < imageList.size(); index++) {
			ImageRecord record = imageList.get(index);
			ImageIcon imgIcon = getImage(record.getThumbnail());
			con.print("\t");
			con.println(imgIcon);
			con.print("\t");
			con.println("Image ID: " + record.getImageId() + "\t");
			con.println("Title: " + record.getTitle() + "\t");
			con.println("Description: " + record.getDescription() + "\t");
			con.println("Genre: " + record.getType() + "\t");
			con.println("Date Taken: " + record.getDateTaken() + "\t\n");
		}
	}

	// Allows the user to add a new image
	private static void addAnImage() {
		con.print("Enter the title of the image: ");
		String title = con.readLn();
		con.print("Enter a description for the image: ");
		String description = con.readLn();
		con.print("Enter the date the image was taken (YYYY-MM-DD): ");
		String date = con.readLn();
		LocalDate dateTaken = LocalDate.parse(date);
		con.print("Enter the thumbnail of the image: ");
		String thumbnail = con.readLn();
		con.println();
		con.println("Image added successfully!\n");

		ImageRecord record;
		try {
			record = new ImageRecord(title, description, ImageType.OTHER, dateTaken, thumbnail);
			img.addImage(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * allows the user to search image records by their id, title, description,
	 * image type or between a specific date range
	 */
	private static void searchImages() {
		try {
			con.println("Please choose by which method you would like to search.");
			con.print("1. ID, 2. Title, 3, Description, 4. Type, 5. Date Range: ");
			String choice = con.readLn();
			int choice1 = Integer.parseInt(choice);

			switch (choice1) {
			case 1:
				con.print("Enter ID: ");
				String id = con.readLn();
				int id1 = Integer.parseInt(id);
				ImageRecord record = img.searchId(id1);
				if (record != null) {
					ImageIcon imgIcon = getImage(record.getThumbnail());
					con.println("Search Result: ");
					displayImage(record, imgIcon);
				} else {
					con.println("No image with id: " + id);
				}
				con.println();
				break;

			case 2:
				con.print("Enter title: ");
				String title = con.readLn();
				ImageAlbum album1 = img.searchTitle(title);
				if (album1 != null) {
					for (ImageRecord record1 : album1.getImageRecords()) {
						ImageIcon imgIcon = getImage(record1.getThumbnail());
						displayImage(record1, imgIcon);
					}
				} else {
					con.println("No image with title: " + title);
				}
				break;

			case 3:
				con.print("Enter description: ");
				String description = con.readLn();
				ImageAlbum album2 = img.searchDescription(description);
				if (album2 != null) {
					for (ImageRecord record2 : album2.getImageRecords()) {
						ImageIcon imgIcon = getImage(record2.getThumbnail());
						displayImage(record2, imgIcon);
					}
				} else {
					con.println("No image with title: " + description);
				}
				con.println();
				break;

			case 4:
				con.print("Enter Type: ");
				String genre = con.readLn();
				ImageType type = ImageType.valueOf(genre.toUpperCase());
				ImageAlbum album3 = img.searchGenre(type);
				if (album3 != null) {
					for (ImageRecord record3 : album3.getImageRecords()) {
						ImageIcon imgIcon = getImage(record3.getThumbnail());
						displayImage(record3, imgIcon);
					}
				} else {
					con.println("No image with genre: " + genre);
				}
				con.println();
				break;

			case 5:
				con.print("Enter start date (YYYY-MM-DD): ");
				String start = con.readLn();
				LocalDate date1 = LocalDate.parse(start);
				con.print("Enter end date (YYYY-MM-DD): ");
				String end = con.readLn();
				LocalDate date2 = LocalDate.parse(end);
				ImageAlbum album4 = img.searchDates(date1, date2);
				if (album4 != null) {
					for (ImageRecord record4 : album4.getImageRecords()) {
						ImageIcon imgIcon = getImage(record4.getThumbnail());
						displayImage(record4, imgIcon);
					}
				} else {
					con.println("No images between " + date1 + " and " + date2);
				}
				con.println();
				break;
			}
		} catch (Exception ex) {
			con.println("Error" + ex);
		}
	}

	/**
	 * Display the details of images in the searchImages method
	 * 
	 * @param record
	 * @param imgIcon
	 */
	private static void displayImage(ImageRecord record, ImageIcon imgIcon) {
		con.print(imgIcon);
		con.println();
		con.println("Search Result:");
		con.println("\tTitle: " + record.getTitle());
		con.println("\tDescription: " + record.getDescription());
		con.println("\tGenre: " + record.getType());
		con.println("\tDate Taken: " + record.getDateTaken());
	}

}