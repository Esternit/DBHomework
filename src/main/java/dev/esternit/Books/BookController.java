package dev.esternit.Books;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "simple book api controller which allows to do CRUD operations")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Returns a list of all books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "Returns a book with the given id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Book with the given id does not exist"),
            @ApiResponse(responseCode = "200", description = "Book with the given id")
    })
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(bookService.getBookById(id).get());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping
    @Operation(summary = "Add book", description = "Adds a new book")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book added successfully"),
            @ApiResponse(responseCode = "400", description = "Book with the given id already exists")
    })
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try{
            bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book", description = "Updates a book with the given id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Book with the given id does not exist")
    })
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book book) {
        try {
            bookService.updateBook(id, book);
            return ResponseEntity.ok(book);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Deletes a book with the given id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Book with the given id does not exist")
    })
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        try{
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
