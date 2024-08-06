package librarymanagementsystem.dtos;

import jakarta.validation.constraints.NotNull;
import librarymanagementsystem.utils.annotations.PastDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@ToString
@NoArgsConstructor
public class BookDto implements Serializable {

    @NotNull
    private Integer bookId;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String isbn;
    @NotNull
    @PastDate(message = "Invalid publication Year")
    private LocalDate publicationYear;
    @NotNull
    private Integer quantity;

    public BookDto(Integer bookId, String title, String author, String isbn, LocalDate publicationYear, Integer quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
    }
}
