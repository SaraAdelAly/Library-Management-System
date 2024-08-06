package library.books.librarymanagementsystem.service;


import librarymanagementsystem.dtos.CreatedPatron;
import librarymanagementsystem.dtos.PatronDto;
import librarymanagementsystem.entities.Patron;
import librarymanagementsystem.mappers.PatronMapper;
import librarymanagementsystem.repositories.PatronRepo;
import librarymanagementsystem.service.PatronService;
import librarymanagementsystem.utils.exceptions.PatronNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronRepo patronRepo;

    @Mock
    private PatronMapper patronMapper;


    @InjectMocks
    private PatronService patronService;

    @Test
    void testGetAllPatrons() {
        List<Patron> patrons = List.of(new Patron());
        when(patronRepo.findAll()).thenReturn(patrons);
        when(patronMapper.toCreatedPatron(any(Patron.class))).thenReturn(new CreatedPatron());

        List<CreatedPatron> createdPatrons = patronService.getAllPatrons();

        assertNotNull(createdPatrons);
        assertEquals(1, createdPatrons.size());
    }

    @Test
    void testGetPatron() {
        Patron patron= new Patron(2, "Jane Smith", "jane.smith@example.com", "555-5678");
        when(patronRepo.findById(1)).thenReturn(Optional.of(patron));
        when(patronMapper.toDto(patron)).thenReturn(new PatronDto(2, "Jane Smith", "jane.smith@example.com", "555-5678"));
        PatronDto result = patronService.getPatron(1);

        assertNotNull(result);
        assertEquals( "Jane Smith",result.getName());
    }

    @Test
    void testGetPatronNotFound() {
        when(patronRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(PatronNotFoundException.class, () -> patronService.getPatron(1));
    }

}
