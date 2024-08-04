package librarymanagementsystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate publicationYear;
    @NotNull
    private Integer quantity;


}
