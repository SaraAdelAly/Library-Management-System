package librarymanagementsystem.service;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.dtos.CreatedPatron;
import librarymanagementsystem.entities.Book;
import librarymanagementsystem.entities.Patron;
import librarymanagementsystem.mappers.BookMapper;
import librarymanagementsystem.mappers.PatronMapper;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.repositories.PatronRepo;
import librarymanagementsystem.utils.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepo patronRepo;
    private final PatronMapper patronMapper;


}
