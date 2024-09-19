package dev.esternit.Books;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private static final String FILE_PATH = "books.json";
    private List<Book> books = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BookService() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                // Проверяем, что файл не пустой
                if (file.length() > 0) {
                    books = objectMapper.readValue(file, new TypeReference<List<Book>>() {});
                } else {
                    books = new ArrayList<>();
                }
            } else {
                file.createNewFile();
                books = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int id) {
        Optional<Book> optionalBook = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("Book with id " + id + " does not exist");
        }

        return optionalBook;
    }

    public void addBook(Book book) {
        for(Book b : books) {
            if (b.getId() == book.getId()) {
                throw new IllegalArgumentException("Book with id " + book.getId() + " already exists");
            }
        }
        books.add(book);
        saveBooks();
    }

    public void updateBook(int id, Book updatedBook) {
        Optional<Book> optionalBook = getBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setFinishedDate(updatedBook.getFinishedDate());
            book.setRating(updatedBook.getRating());
            saveBooks();
        }
    }

    public void deleteBook(int id) {
        Optional<Book> optionalBook = getBookById(id);

        if (optionalBook.isPresent()) {
            books.remove(optionalBook.get());
            saveBooks();
        }
    }

    private void saveBooks() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
