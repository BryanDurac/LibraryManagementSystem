package part01;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The object class ImageManager will be responsible for managing a collection
 * of ImageRecord instances.
 * 
 * @author Bryan Durac
 */

public class ImageManager {

	private ArrayList<ImageRecord> imageRecord; // stores a list of image records
	static ImageAlbum album; // Defines an instance of ImageAlbum

	/**
	 * This is the constructor that will be used to store image records
	 */
	public ImageManager() {
		ImageManager.album = new ImageAlbum();
		this.imageRecord = new ArrayList<ImageRecord>();
	}

	/**
	 * Adds a new image record
	 * 
	 * @param image
	 */
	public void addImage(ImageRecord image) {
		if (image != null) {
			imageRecord.add(image);
		}
	}

	/**
	 * Allows the user to search for an image record by id
	 * 
	 * @param id
	 * @return
	 */
	public ImageRecord searchId(int id) {
		ImageRecord target = null;
		for (int index = 0; index < imageRecord.size(); index++) {
			ImageRecord img = imageRecord.get(index);
			if (img.getImageId() == id) {
				target = img;
				break;
			}
		}
		return target;
	}

	/**
	 * Allows the user to search for an image record by title
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchTitle(String str) {
		ImageAlbum album = new ImageAlbum();
		for (ImageRecord record : imageRecord) {
			if (record.getTitle().contains(str)) {
				album.addImageRecord(record);
			}
		}
		return album;
	}

	/**
	 * Allows the user to search for an image record by description
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchDescription(String str) {
		ImageAlbum album = new ImageAlbum();
		for (ImageRecord record : imageRecord) {
			if (record.getDescription().contains(str)) {
				album.addImageRecord(record);
			}
		}
		return album;
	}

	/**
	 * Allows the user to search for an image record by genre
	 * 
	 * @param type
	 * @return
	 */
	public ImageAlbum searchGenre(ImageType type) {
		ImageAlbum album = new ImageAlbum();
		for (ImageRecord record : imageRecord) {
			if (record.getType().equals(type)) {
				album.addImageRecord(record);
			}
		}
		return album;
	}

	/**
	 * Allows the user to search for an image record by start and end date
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ImageAlbum album = new ImageAlbum();
		for (ImageRecord record : imageRecord) {
			LocalDate dateTaken = record.getDateTaken();
			if (!dateTaken.isBefore(start) && !dateTaken.isAfter(end)) {
				album.addImageRecord(record);
			}
		}
		return album;
	}

	/**
	 * Return all the image records
	 * 
	 * @return
	 */
	public ImageAlbum getAllImages() {
		ImageAlbum album = new ImageAlbum();
		for (ImageRecord record : imageRecord) {
			album.addImageRecord(record);
		}
		return album;
	}

}