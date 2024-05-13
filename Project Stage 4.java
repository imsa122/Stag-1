import java.util.ArrayList;
import java.util.List;

// Strategy pattern implementation
interface BookDisplayStrategy {
    void display(Book book);
}

class SimpleBookDisplayStrategy implements BookDisplayStrategy {
    @Override
    public void display(Book book) {
        System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
    }
}

class DetailedBookDisplayStrategy implements BookDisplayStrategy {
    @Override
    public void display(Book book) {
        System.out.println("Detailed Information:");
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        // Additional details can be added here
    }
}

// State pattern implementation
interface LibraryState {
    void displayLibrary(Library library, BookDisplayStrategy displayStrategy);
}

class OpenState implements LibraryState {
    @Override
    public void displayLibrary(Library library, BookDisplayStrategy displayStrategy) {
        System.out.println("Library is open. Displaying library inventory:");
        for (Book book : library.getBooks()) {
            displayStrategy.display(book);
        }
    }
}

class ClosedState implements LibraryState {
    @Override
    public void displayLibrary(Library library, BookDisplayStrategy displayStrategy) {
        System.out.println("Sorry, the library is closed.");
    }
}

class Library {
    private List<Book> books;
    private LibraryState currentState;

    public Library() {
        this.books = new ArrayList<>();
        // Initially, the library is open
        this.currentState = new OpenState();
    }

    public void changeState(LibraryState newState) {
        this.currentState = newState;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayLibrary(BookDisplayStrategy displayStrategy) {
        currentState.displayLibrary(this, displayStrategy);
    }

    public List<Book> getBooks() {
        return books;
    }
}

class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        // Creating books
        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee");
        Book book3 = new Book(3, "1984", "George Orwell");

        // Creating a library
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Using different display strategies
        BookDisplayStrategy simpleDisplayStrategy = new SimpleBookDisplayStrategy();
        BookDisplayStrategy detailedDisplayStrategy = new DetailedBookDisplayStrategy();

        // Displaying library using simple display strategy
        System.out.println("Displaying Library with Simple Display Strategy:");
        library.displayLibrary(simpleDisplayStrategy);

        // Changing library state to closed
        System.out.println("\nClosing the library...");
        library.changeState(new ClosedState());

        // Displaying library when it's closed
        System.out.println("\nDisplaying Library when it's closed:");
        library.displayLibrary(simpleDisplayStrategy); // You can use either simpleDisplayStrategy or detailedDisplayStrategy here
    }
}
