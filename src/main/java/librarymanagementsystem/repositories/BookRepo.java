package librarymanagementsystem.repositories;

import librarymanagementsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {




}
