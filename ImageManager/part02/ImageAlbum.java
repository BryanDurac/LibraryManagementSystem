package part02;

import java.util.ArrayList;

/**
 * An object class ImageAlbum that holds image records
 * 
 * @author Bryan Durac
 */

public class ImageAlbum {
	private ArrayList<ImageRecord> imageRecords; // stores a list of image records
	private int position = 0; // keeps track of the current image record

	/**
	 * The contructor for ImageAlbum sorts the image records by date
	 */
	public ImageAlbum() {
		this.imageRecords = new ArrayList<ImageRecord>();
		sortImageRecordsByDate(imageRecords);
	}

	/**
	 * Adds a new image record
	 * 
	 * @param imageRecord
	 */
	public void addImageRecord(ImageRecord imageRecord) {
		imageRecords.add(imageRecord);
	}

	/**
	 * returns the image records
	 * 
	 * @return
	 */
	public ArrayList<ImageRecord> getImageRecords() {
		return imageRecords;
	}

	/**
	 * returns the images in ImageAlbum
	 */
	public String toString() {
		String result = "";
		if (this.imageRecords.size() > 0) {
			for (int i = 0; i < imageRecords.size(); i++) {
				ImageRecord temp = imageRecords.get(i);
				result += temp.toString() + "\n";
			}
		} else {
			result += "The album is empty.\n";
		}
		return result;
	}

	/**
	 * Sorts the image records by date
	 * 
	 * @param imageRecords
	 */
	private void sortImageRecordsByDate(ArrayList<ImageRecord> imageRecords) {
		for (ImageRecord record : imageRecords) {
			boolean added = false;
			for (int i = 0; i < this.imageRecords.size(); i++) {
				if (record.getDateTaken().compareTo(this.imageRecords.get(i).getDateTaken()) < 0) {
					this.imageRecords.add(i, record);
					added = true;
					break;
				}
			}
			if (!added) {
				this.imageRecords.add(record);
			}
		}
	}

	// Displays the first image record in ImageAlbum
	public ImageRecord getFirst() {
		if (imageRecords != null) {
			position = 0;
			return imageRecords.get(position);
		} else {
			return null;
		}
	}

	// Displays the next image record in ImageAlbum
	public ImageRecord getNext() {
		if (imageRecords != null) {
			if (position >= imageRecords.size() - 1) {
				return null;
			}
			position++;
			return this.imageRecords.get(position);
		}
		return null;
	}

	// Displays the previous image record in ImageAlbum
	public ImageRecord getPrevious() {
		if (imageRecords != null && position > 0) {
			position--;
			return imageRecords.get(position);
		} else {
			return null;
		}
	}

}