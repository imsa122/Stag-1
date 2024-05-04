import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

    // Singleton pattern implementation
    public static class LibrarySingleton {
        private static LibrarySingleton instance;
        private List<Book> books;

        private LibrarySingleton() {
            books = new ArrayList<>();
        }

        public static LibrarySingleton getInstance() {
            if (instance == null) {
                instance = new LibrarySingleton();
            }
            return instance;
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void displayLibrary() {
            System.out.println("Library Inventory:");
            for (Book book : books) {
                book.displayBookInfo();
            }
        }
    }

    // Factory pattern implementation
    public static class BookFactory {
        public static Book createBook(int bookId, String title, String author) {
            return new Book(bookId, title, author);
        }
    }

    public static class Book {
        private int bookId;
        private String title;
        private String author;

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        public void displayBookInfo() {
            System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author);
        }
    }

    public static class Borrower {
        private String name;
        private List<Book> borrowedBooks;

        public Borrower(String name) {
            this.name = name;
            this.borrowedBooks = new ArrayList<>();
        }

        public void borrowBook(Book book) {
            borrowedBooks.add(book);
            System.out.println(name + " has borrowed the book: " + book.title);
        }

        public void displayBorrowedBooks() {
            System.out.println(name + "'s Borrowed Books:");
            for (Book book : borrowedBooks) {
                book.displayBookInfo();
            }
        }
    }

    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        // Creating books using factory
        Book book1 = BookFactory.createBook(1, "The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = BookFactory.createBook(2, "To Kill a Mockingbird", "Harper Lee");
        Book book3 = BookFactory.createBook(3, "1984", "George Orwell");

        // Creating a library using singleton
        LibrarySingleton library = LibrarySingleton.getInstance();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Creating a borrower and borrowing books
        Borrower borrower = new Borrower("Alice");
        borrower.borrowBook(book1);
        borrower.borrowBook(book2);

        // Creating a person
        Person person = new Person("Bob");

        // Displaying library inventory, borrower's borrowed books, and person's name
        library.displayLibrary();
        borrower.displayBorrowedBooks();
        System.out.println("Person's name: " + person.getName());
    }
}
