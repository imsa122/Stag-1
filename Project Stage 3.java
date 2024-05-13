import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

    // Decorator pattern implementation
    public interface BookDecorator {
        void display();
    }

    public static class BaseBook implements BookDecorator {
        private Book book;

        public BaseBook(Book book) {
            this.book = book;
        }

        @Override
        public void display() {
            book.displayBookInfo();
        }
    }

    public static class BorrowedBookDecorator implements BookDecorator {
        private Book book;

        public BorrowedBookDecorator(Book book) {
            this.book = book;
        }

        @Override
        public void display() {
            book.displayBookInfo();
            System.out.println("(Borrowed)");
        }
    }

    // Proxy pattern implementation
    public interface BookService {
        void displayLibrary();
    }

    public static class RealBookService implements BookService {
        private Library library;

        public RealBookService(Library library) {
            this.library = library;
        }

        @Override
        public void displayLibrary() {
            library.displayLibrary();
        }
    }

    public static class LibraryProxy implements BookService {
        private RealBookService realBookService;

        public LibraryProxy(Library library) {
            this.realBookService = new RealBookService(library);
        }

        @Override
        public void displayLibrary() {
            System.out.println("Proxy is performing additional checks...");
            realBookService.displayLibrary();
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

    public static class Library {
        private List<Book> books;

        public Library() {
            this.books = new ArrayList<>();
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
        // Creating books
        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee");
        Book book3 = new Book(3, "1984", "George Orwell");

        // Creating a library
        Library library = new Library();
        library.addBook(book3);

        // Decorator pattern: decorating borrowed books
        BookDecorator borrowedBook1 = new BorrowedBookDecorator(book1);
        BookDecorator borrowedBook2 = new BorrowedBookDecorator(book2);

        // Displaying borrowed books
        System.out.println("Borrowed Books:");
        borrowedBook1.display();
        borrowedBook2.display();

        // Proxy pattern: using a proxy for library access
        BookService libraryProxy = new LibraryProxy(library);
        libraryProxy.displayLibrary();
    }
}
