# Library Management System

**Course:** CSC 1102 – Structured and Object-Oriented Programming  
**Type:** Practical Exam Project  

---

## Group Details

| Name                    | Student Number     | Role            |
|-------------------------|--------------------|-----------------|
| [Leader Full Name]      | [e.g. 22007xxxx]   | Group Leader    |
| [Member 2 Full Name]    | [Student Number]   | Member          |
| [Member 3 Full Name]    | [Student Number]   | Member          |
| [Member 4 Full Name]    | [Student Number]   | Member          |

**GitHub Repository:** https://github.com/byamugishatony/SIMPLE-Library-Management-System
---

## Project Description

This is a simple **Library Management System** developed in Java using the Swing framework.  
The application allows a librarian to manage books and members. Users can add books, register members, search for books, borrow and return books, and view lists of books and members.

The system stores data in arrays (no database is used) and demonstrates Object-Oriented Programming concepts such as classes, objects, encapsulation, constructors, getters, and setters.

---

## Main Features

- Add a new book
- Register a new member
- View all books
- View available books only
- Search books by title, author, or ISBN
- Borrow a book
- Return a book
- View all registered members

---

## Classes Used

| Class         | Purpose                                      |
|---------------|----------------------------------------------|
| `Book`        | Represents a single book                     |
| `Member`      | Represents a library member                  |
| `Library`     | Manages the arrays of books and members and contains the business logic |
| `LibraryGUI`  | Provides the Graphical User Interface using Java Swing |

---

## How the Classes Interact

- The `LibraryGUI` class creates a `Library` object.
- The `Library` class creates and stores `Book` and `Member` objects in arrays.
- When the user clicks a button in the GUI, the corresponding method in the `Library` class is called.
- The `Library` class uses loops and conditions to search, update, and display the data.

---

## How to Run the Project

1. Make sure you have **Java JDK** installed.
2. Open a terminal in the project folder.
3. Compile the files:
   ```bash
   javac *.java
