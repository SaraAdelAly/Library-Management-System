package librarymanagementsystem.service;

import librarymanagementsystem.entities.Book;
import librarymanagementsystem.entities.Borrowing;
import librarymanagementsystem.entities.Patron;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.repositories.BorrowingRepo;
import librarymanagementsystem.repositories.PatronRepo;
import librarymanagementsystem.utils.exceptions.BookNotFoundException;
import librarymanagementsystem.utils.exceptions.PatronNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BookRepo bookRepo;
    private final PatronRepo patronRepo;
    private final BorrowingRepo borrowingRepo;

    public void borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException(patronId));

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setPatron(patron);
        borrowing.setBorrowingDate(LocalDate.now());
        borrowing.setReturnDate(LocalDate.now().plusMonths(1));

        borrowingRepo.save(borrowing);
    }


}
