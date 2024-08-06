package librarymanagementsystem.controller;

import librarymanagementsystem.service.BorrowingService;
import librarymanagementsystem.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BorrowingController {

    private final BorrowingService borrowingService;

    private static final Logger logger = LoggerFactory.getLogger(BorrowingController.class);

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        try {
            borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book borrowed successfully");
        } catch (BookNotAvailableException | PatronNotFoundException ex) {
            logger.error("Error occurred: ", ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (PatronHasOverdueBooksException ex) {
            logger.error("Error occurred: ", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error occurred: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        try {
            borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (BorrowingNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ReturnDateExceededException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred while processing the request");
        }
    }

}
