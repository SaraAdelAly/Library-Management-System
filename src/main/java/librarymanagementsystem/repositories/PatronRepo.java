package librarymanagementsystem.repositories;

import librarymanagementsystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepo extends JpaRepository<Patron,Integer> {


}
