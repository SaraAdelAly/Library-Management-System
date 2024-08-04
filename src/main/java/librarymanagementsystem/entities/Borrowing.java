package librarymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="borrowing")
public class Borrowing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id")
    private Integer borrowingId;

    @Column(name="borrowing_date")
    private LocalDate borrowingDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "patron_id",nullable = false)
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(borrowingId, borrowing.borrowingId) && Objects.equals(borrowingDate, borrowing.borrowingDate) && Objects.equals(returnDate, borrowing.returnDate) && Objects.equals(patron, borrowing.patron) && Objects.equals(book, borrowing.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingId, borrowingDate, returnDate, patron, book);
    }
}
