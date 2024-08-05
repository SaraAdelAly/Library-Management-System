package librarymanagementsystem.utils.advice;

import librarymanagementsystem.entities.Book;
import librarymanagementsystem.service.BookService;
import librarymanagementsystem.utils.exceptions.BookNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class BookQuantityAspect {

    private final BookService bookService;


    @Around(value = "execution(* librarymanagementsystem.service.BorrowingService.borrowBook(..)) && args(bookId, patronId)", argNames = "joinPoint,bookId,patronId")
    public Object aroundBorrowBook(ProceedingJoinPoint joinPoint, Integer bookId, Integer patronId) throws Throwable {
        Book book = bookService.findBook(bookId)
                .orElseThrow(() -> new BookNotAvailableException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Book is not available for borrowing");
        }

        Object result = joinPoint.proceed();
        book.setQuantity(book.getQuantity() - 1);
        bookService.addBook(book);
        return result;
    }
}
