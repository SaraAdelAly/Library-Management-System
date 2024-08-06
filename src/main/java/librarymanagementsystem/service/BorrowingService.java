package librarymanagementsystem.service;

import librarymanagementsystem.entities.Book;
import librarymanagementsystem.entities.Borrowing;
import librarymanagementsystem.entities.Patron;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.repositories.BorrowingRepo;
import librarymanagementsystem.repositories.PatronRepo;
import librarymanagementsystem.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BookRepo bookRepo;
    private final PatronRepo patronRepo;
    private final BorrowingRepo borrowingRepo;

    @Transactional
    public void borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException(patronId));

        List<Borrowing> borrowings = borrowingRepo.findByPatronAndReturnDateIsNull(patron);

        boolean hasOverdueBooks = borrowings.stream()
                .anyMatch(borrowing -> {
                    LocalDate borrowingDate = borrowing.getBorrowingDate();
                    long daysBetween = ChronoUnit.DAYS.between(borrowingDate, LocalDate.now());
                    return daysBetween > 30;
                });
        if (hasOverdueBooks) {
            throw new PatronHasOverdueBooksException("Patron has overdue books and cannot borrow a new book.");
        }
        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setPatron(patron);
        borrowing.setBorrowingDate(LocalDate.now());

        borrowingRepo.save(borrowing);
    }


    @Transactional
    public void returnBook(Integer bookId, Integer patronId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException(patronId));

        Borrowing borrowing = borrowingRepo.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new BorrowingNotFoundException("this patron did not borrow this book"));

        LocalDate today = LocalDate.now();
        LocalDate borrowingDate = borrowing.getBorrowingDate();
        long daysBetween = ChronoUnit.DAYS.between(borrowingDate, today);

        if (daysBetween > 30) {
            throw new ReturnDateExceededException("The return date exceeds the allowed period of one month.");
        }

        borrowing.setReturnDate(today);
        borrowingRepo.save(borrowing);

        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);
    }


}
