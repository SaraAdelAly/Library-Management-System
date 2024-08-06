package librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="book_id",nullable = false)
    private Integer bookId;


    @Column (name="book_title",nullable = false)
    private String title;

    @Column (name="author",nullable = false)
    private String author;
    @Column (name="isbn",nullable = false)
    private String isbn;
    @Column (name="publication_year",nullable = false)
    private LocalDate publicationYear;
    @Column (name="quantity",nullable = false)
    @Check(constraints = "quantity >= 0")
    private Integer quantity;

    @OneToMany(mappedBy = "book")
    private Set<Borrowing> borrowings;

    public Book(Integer bookId, String title, String author, String isbn, LocalDate publicationYear, Integer quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(publicationYear, book.publicationYear) && Objects.equals(quantity, book.quantity) && Objects.equals(borrowings, book.borrowings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, isbn, publicationYear, quantity, borrowings);
    }
}
