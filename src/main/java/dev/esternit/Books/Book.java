package dev.esternit.Books;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Schema(description = "Book model")
public class Book {
    @Schema(description = "Book id", example = "0")
    private int id;
    @Schema(description = "Book title", example = "Sir. Martin")
    private String title;
    @Schema(description = "Book author", example = "Nikolai Lobachevsky")
    private String author;
    @Schema(description = "Book finished date", example = "2024-09-19")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date finishedDate;
    @Schema(description = "Book rating", example = "1")
    private int rating;

    public Book(int id, String title, String author, Date finishedDate, int rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.finishedDate = finishedDate;
        this.rating = rating;
    }
    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
