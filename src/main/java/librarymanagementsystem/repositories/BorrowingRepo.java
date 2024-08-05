package librarymanagementsystem.repositories;

import librarymanagementsystem.entities.Book;
import librarymanagementsystem.entities.Borrowing;
import librarymanagementsystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepo extends JpaRepository<Borrowing,Integer> {

    Optional<Borrowing> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
    List<Borrowing> findByPatronAndReturnDateIsNull(Patron patron);
}
