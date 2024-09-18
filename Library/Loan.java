package Library;

import java.time.LocalDate;

public class Loan {

	private String itemBarcode;
	private String userID;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private int numberOfRenewals;

	public Loan(String itemBarcode, String userID, LocalDate issueDate, LocalDate dueDate, int numberOfRenewals) {
		this.itemBarcode = itemBarcode;
		this.userID = userID;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.numberOfRenewals = 0;
	}

	public String getItemBarcode() {
		return this.itemBarcode;
	}

	public String getUserID() {
		return this.userID;
	}

	public LocalDate getIssueDate() {
		return this.issueDate;
	}

	public LocalDate getDueDate() {
		return this.dueDate;
	}

	public int getNumberOfRenewals() {
		return this.numberOfRenewals;
	}

	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate setIssueDueDate(Item item) {
		if (item.getItemType().equalsIgnoreCase("Book")) {
			return this.dueDate = LocalDate.now().plusWeeks(4);
		} else {
			return this.dueDate = LocalDate.now().plusWeeks(1);
		}
	}

	public void setNumberOfRenewals(int numberOfRenewals) {
		this.numberOfRenewals = numberOfRenewals;
	}

	public String toString() {
		String loanDetails = "Loan Details:\n";
		loanDetails += "Barcode: " + getItemBarcode() + "\n";
		loanDetails += "User ID: " + getUserID() + "\n";
		loanDetails += "Issue Date: " + getIssueDate() + "\n";
		loanDetails += "Due Date: " + getDueDate() + "\n";
		loanDetails += "Number of Renewals: " + getNumberOfRenewals() + "\n";
		return loanDetails;
	}

}