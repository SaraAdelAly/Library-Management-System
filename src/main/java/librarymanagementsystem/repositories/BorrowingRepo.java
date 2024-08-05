package librarymanagementsystem.repositories;

import librarymanagementsystem.entities.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepo extends JpaRepository<Borrowing,Integer> {
}
