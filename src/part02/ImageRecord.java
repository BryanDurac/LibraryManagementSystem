package part02;

import java.time.LocalDate;

/**
 * An object class to describe ImageRecords
 * 
 * @author Bryan Durac
 */

public class ImageRecord {

	private int imageId; // uniquely identifies the image
	private String title; // the title of the image
	private String description; // the description of the image
	private ImageType type; // the type of the image
	private LocalDate dateTaken; // the date the image was taken
	private String thumbnail; // the thumbnail of the image
	private static int nextId = 1; // the id of the next image

	/**
	 * This is the constructor that will be used to instantiate image records
	 * 
	 * @param title
	 * @param description
	 * @param type
	 * @param dateTaken
	 * @param thumbnail
	 * @throws Exception
	 */
	public ImageRecord(String title, String description, ImageType type, LocalDate dateTaken, String thumbnail) throws Exception {
		this.imageId = nextId;
		nextId++;

		String errors = "";

		if (setTitle(title) != true) {
			errors += "Invalid Title " + title + "\n";
		}

		if (setDescription(description) != true) {
			errors += "Invalid Description " + description + "\n";
		}

		if (setType(type) != true) {
			errors += "Invalid Genre " + type + "\n";
		}

		if (setDateTaken(dateTaken) != true) {
			errors += "Invalid Date " + dateTaken + "\n";
		}

		if (setThumbnail(thumbnail) != true) {
			errors += "Invalid Thumbnail " + thumbnail + "\n";
		}

		if (errors.length() > 0) {
			throw new Exception(errors);
		}
	}

	// The following getters return details about a specific image record
	public int getImageId() {
		return this.imageId;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public ImageType getType() {
		return this.type;
	}

	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	// The following setters enable users to set values for an image record
	public boolean setTitle(String title) {
		if (title != null && title.length() > 0) {
			this.title = title;
			return true;
		}
		return false;
	}

	public boolean setDescription(String description) {
		if (description != null && description.length() > 0) {
			this.description = description;
			return true;
		}
		return false;
	}

	public boolean setType(ImageType type) {
		if (type != null) {
			this.type = type;
			return true;
		}
		return false;
	}

	public boolean setDateTaken(LocalDate dateTaken) {
		if (dateTaken != null) {
			this.dateTaken = dateTaken;
			return true;
		}
		return false;
	}

	public boolean setThumbnail(String thumbnail) {
		if (thumbnail != null && thumbnail.length() > 0) {
			this.thumbnail = thumbnail;
			return true;
		}
		return false;
	}

	/**
	 * Returns all the details of an image record
	 */
	public String toString() {
		String data = "Image ID: " + getImageId() + ", Title: " + getTitle() + ", Description: " + getDescription()
				+ ", Genre: " + getType() + ", Date Taken: " + getDateTaken() + ", Thumbnail: " + getThumbnail();
		return data;
	}

}