/**
 * Manages the collection of books and members using arrays.
 * Contains all the business logic.
 */
public class Library {
    private static final int MAX_BOOKS = 50;
    private static final int MAX_MEMBERS = 30;

    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;

    public Library() {
        books = new Book[MAX_BOOKS];
        members = new Member[MAX_MEMBERS];
        bookCount = 0;
        memberCount = 0;
    }

    // ---------- Book operations ----------

    public boolean addBook(String title, String author, String isbn) {
        if (bookCount >= MAX_BOOKS) {
            return false; // array full
        }
        // Check for duplicate ISBN
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equalsIgnoreCase(isbn)) {
                return false; // ISBN already exists
            }
        }
        books[bookCount] = new Book(title, author, isbn);
        bookCount++;
        return true;
    }

    public String getAllBooks() {
        if (bookCount == 0) return "No books in the library.";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s | %-20s | %-12s | %s\n", "TITLE", "AUTHOR", "ISBN", "STATUS"));
        sb.append("--------------------------------------------------------------------------------\n");
        for (int i = 0; i < bookCount; i++) {
            sb.append(books[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public String getAvailableBooks() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        sb.append(String.format("%-30s | %-20s | %-12s | %s\n", "TITLE", "AUTHOR", "ISBN", "STATUS"));
        sb.append("--------------------------------------------------------------------------------\n");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                sb.append(books[i].toString()).append("\n");
                found = true;
            }
        }
        if (!found) return "No available books at the moment.";
        return sb.toString();
    }

    public Book findBookByIsbn(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equalsIgnoreCase(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public String searchBooks(String keyword) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        keyword = keyword.toLowerCase();

        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            if (b.getTitle().toLowerCase().contains(keyword) ||
                b.getAuthor().toLowerCase().contains(keyword) ||
                b.getIsbn().toLowerCase().contains(keyword)) {
                sb.append(b.toString()).append("\n");
                found = true;
            }
        }
        if (!found) return "No books matched your search.";
        return sb.toString();
    }

    // ---------- Member operations ----------

    public boolean registerMember(String id, String name, String phone) {
        if (memberCount >= MAX_MEMBERS) return false;

        // Check duplicate ID
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        members[memberCount] = new Member(id, name, phone);
        memberCount++;
        return true;
    }

    public String getAllMembers() {
        if (memberCount == 0) return "No members registered.";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s | %-20s | %s\n", "ID", "NAME", "PHONE"));
        sb.append("-----------------------------------------------\n");
        for (int i = 0; i < memberCount; i++) {
            sb.append(members[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public Member findMemberById(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equalsIgnoreCase(id)) {
                return members[i];
            }
        }
        return null;
    }

    // ---------- Borrow / Return ----------

    public String borrowBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        if (book == null) return "Book not found.";
        if (!book.isAvailable()) return "Book is already borrowed.";

        Member member = findMemberById(memberId);
        if (member == null) return "Member not found. Please register first.";

        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        return "Book borrowed successfully by " + member.getName();
    }

    public String returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) return "Book not found.";
        if (book.isAvailable()) return "This book is not currently borrowed.";

        book.setAvailable(true);
        book.setBorrowedBy(null);
        return "Book returned successfully.";
    }
}