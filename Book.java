/**
 * Represents a single book in the library.
 */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String borrowedBy;   // stores Member ID when borrowed

    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;   // new books start as available
        this.borrowedBy = null;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public String getBorrowedBy() { return borrowedBy; }

    // Setters
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setBorrowedBy(String memberId) { this.borrowedBy = memberId; }

    // Nice string representation for display
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed by: " + borrowedBy;
        return String.format("%-30s | %-20s | %-12s | %s", title, author, isbn, status);
    }
}