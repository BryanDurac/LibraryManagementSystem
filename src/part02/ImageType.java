package part02;

/**
 * Displays the image type alongside descriptions for the image records.
 * 
 * @author Bryan Durac
 */
public enum ImageType {

	// The different image type enumerations
	ASTRONOMY("Photography or imaging of astronomical objects, celestial events, or areas of the night sky."),
	ARCHITECTURE("Focuses on the capture of images that accurately represent the design and feel of buildings."),
	SPORT("Covers all types of sports and can be considered a branch of photojournalism."),
	LANDSCAPE("The study of the textured surface of the Earth and features images of natural scenes."),
	PORTRAIT("Images of a person or a group of people where the face and facial features are predominant."),
	NATURE("Focused on elements of the outdoors including sky, water, and land, or the flora and fauna."),
	AERIAL("Images taken from an aircraft or other airborne platforms."),
	FOOD("Captures everything related to food, from fresh ingredients and plated dishes to the cooking process."),
	OTHER("Covers just about any other type of image and photography genre.");

	private String info; // instance data

	/**
	 * Constructor for image types
	 * 
	 * @param str - description for the image record
	 */
	private ImageType(String str) {
		this.info = str;
	}

	/**
	 * Return the image type description of the image record
	 */
	public String toString() {
		return this.info;
	}

}