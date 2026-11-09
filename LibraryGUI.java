import java.awt.*;
import javax.swing.*;

/**
 * Main GUI class for the Library Management System.
 * This is the class you run.
 */
public class LibraryGUI extends JFrame {

    private Library library;
    private JTextArea outputArea;

    public LibraryGUI() {
        library = new Library();   // create the library object

        setTitle("Library Management System - CSC 1102");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        // ===== Layout =====
        setLayout(new BorderLayout(10, 10));

        // Left panel – buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        JButton btnAddBook       = new JButton("1. Add Book");
        JButton btnRegister      = new JButton("2. Register Member");
        JButton btnViewAll       = new JButton("3. View All Books");
        JButton btnAvailable     = new JButton("4. View Available Books");
        JButton btnSearch        = new JButton("5. Search Book");
        JButton btnBorrow        = new JButton("6. Borrow Book");
        JButton btnReturn        = new JButton("7. Return Book");
        JButton btnMembers       = new JButton("8. View Members");
        JButton btnExit          = new JButton("9. Exit");

        // Add buttons to panel
        buttonPanel.add(btnAddBook);
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnViewAll);
        buttonPanel.add(btnAvailable);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnBorrow);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnMembers);
        buttonPanel.add(btnExit);

        // Center – output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // ===== Event listeners =====
        btnAddBook.addActionListener(e -> addBook());
        btnRegister.addActionListener(e -> registerMember());
        btnViewAll.addActionListener(e -> showMessage(library.getAllBooks()));
        btnAvailable.addActionListener(e -> showMessage(library.getAvailableBooks()));
        btnSearch.addActionListener(e -> searchBook());
        btnBorrow.addActionListener(e -> borrowBook());
        btnReturn.addActionListener(e -> returnBook());
        btnMembers.addActionListener(e -> showMessage(library.getAllMembers()));
        btnExit.addActionListener(e -> System.exit(0));

        // Welcome message
        showMessage("Welcome to the Library Management System!\n\n" +
                    "Select an option from the buttons on the left.");
    }

    // ---------- Helper methods ----------

    private void showMessage(String message) {
        outputArea.setText(message);
    }

    private void addBook() {
        String title  = JOptionPane.showInputDialog(this, "Enter book title:");
        if (title == null || title.trim().isEmpty()) return;

        String author = JOptionPane.showInputDialog(this, "Enter author:");
        if (author == null || author.trim().isEmpty()) return;

        String isbn   = JOptionPane.showInputDialog(this, "Enter ISBN:");
        if (isbn == null || isbn.trim().isEmpty()) return;

        boolean success = library.addBook(title.trim(), author.trim(), isbn.trim());
        if (success) {
            showMessage("Book added successfully!\n\n" + library.getAllBooks());
        } else {
            showMessage("Failed to add book.\nPossible reasons:\n" +
                        "- Library is full\n- ISBN already exists");
        }
    }

    private void registerMember() {
        String id    = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (id == null || id.trim().isEmpty()) return;

        String name  = JOptionPane.showInputDialog(this, "Enter full name:");
        if (name == null || name.trim().isEmpty()) return;

        String phone = JOptionPane.showInputDialog(this, "Enter phone number:");
        if (phone == null || phone.trim().isEmpty()) return;

        boolean success = library.registerMember(id.trim(), name.trim(), phone.trim());
        if (success) {
            showMessage("Member registered successfully!\n\n" + library.getAllMembers());
        } else {
            showMessage("Failed to register member.\nPossible reasons:\n" +
                        "- Maximum members reached\n- Member ID already exists");
        }
    }

    private void searchBook() {
        String keyword = JOptionPane.showInputDialog(this, "Enter title, author or ISBN to search:");
        if (keyword == null || keyword.trim().isEmpty()) return;
        showMessage(library.searchBooks(keyword.trim()));
    }

    private void borrowBook() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of the book to borrow:");
        if (isbn == null || isbn.trim().isEmpty()) return;

        String memberId = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (memberId == null || memberId.trim().isEmpty()) return;

        String result = library.borrowBook(isbn.trim(), memberId.trim());
        showMessage(result + "\n\n" + library.getAllBooks());
    }

    private void returnBook() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of the book to return:");
        if (isbn == null || isbn.trim().isEmpty()) return;

        String result = library.returnBook(isbn.trim());
        showMessage(result + "\n\n" + library.getAllBooks());
    }

    // ---------- Main method ----------
    public static void main(String[] args) {
        // Group details (put your real details here)
        /*
         * Group Name: ..............
         * Members:
         * 1. Name - Student No
         * 2. Name - Student No
         * 3. Name - Student No
         * GitHub: https://github.com/....
         */

        SwingUtilities.invokeLater(() -> {
            new LibraryGUI().setVisible(true);
        });
    }
}