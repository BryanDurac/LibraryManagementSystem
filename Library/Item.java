package Library;

public class Item {

	private String itemBarcode;
	private String itemAuthor;
	private String itemTitle;
	private String itemType;
	private String itemYear;
	private String itemISBN;

	public Item(String itemBarcode, String itemAuthor, String itemTitle, String itemType, String itemYear, String itemISBN) {
		this.itemBarcode = itemBarcode;
		this.itemAuthor = itemAuthor;
		this.itemTitle = itemTitle;
		this.itemType = itemType;
		this.itemYear = itemYear;
		this.itemISBN = itemISBN;
	}

	public String getItemBarcode() {
		return this.itemBarcode;
	}

	public String getItemAuthor() {
		return this.itemAuthor;
	}

	public String getItemTitle() {
		return this.itemTitle;
	}

	public String getItemType() {
		return this.itemType;
	}

	public String getItemYear() {
		return this.itemYear;
	}

	public String getItemISBN() {
		return this.itemISBN;
	}

	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemYear(String itemYear) {
		this.itemYear = itemYear;
	}

	public void setItemISBN(String itemISBN) {
		this.itemISBN = itemISBN;
	}

	public String toString() {
		String itemDetails = "Item Details:\n";
		itemDetails += "Barcode: " + getItemBarcode() + "\n";
		itemDetails += "Author/Artist: " + getItemAuthor() + "\n";
		itemDetails += "Title: " + getItemTitle() + "\n";
		itemDetails += "Type: " + getItemType() + "\n";
		itemDetails += "Year: " + getItemYear() + "\n";
		itemDetails += "ISBN: " + getItemISBN() + "\n";
		return itemDetails;
	}

	class Book extends Item {

		public Book(String itemBarcode, String itemAuthor, String itemTitle, String itemType, String itemYear,
				String itemISBN) {
			super(itemBarcode, itemAuthor, itemTitle, itemType, itemYear, itemISBN);
		}

		@Override
		public String toString() {
			String itemDetails = "Item Details:\n";
			itemDetails += "Barcode: " + getItemBarcode() + "\n";
			itemDetails += "Author: " + getItemAuthor() + "\n";
			itemDetails += "Title: " + getItemTitle() + "\n";
			itemDetails += "Type: " + getItemType() + "\n";
			itemDetails += "Year: " + getItemYear() + "\n";
			itemDetails += "ISBN: " + getItemISBN() + "\n";
			return itemDetails;
		}
	}

	class Multimedia extends Item {

		public Multimedia(String itemBarcode, String itemAuthor, String itemTitle, String itemType, String itemYear,
				String itemISBN) {
			super(itemBarcode, itemAuthor, itemTitle, itemType, itemYear, itemISBN);
		}

		@Override
		public String toString() {
			String itemDetails = "Item Details:\n";
			itemDetails += "Barcode: " + getItemBarcode() + "\n";
			itemDetails += "Artist: " + getItemAuthor() + "\n";
			itemDetails += "Title: " + getItemTitle() + "\n";
			itemDetails += "Type: " + getItemType() + "\n";
			itemDetails += "Year: " + getItemYear() + "\n";
			itemDetails += "ISBN: " + getItemISBN() + "\n";
			return itemDetails;
		}
	}

}