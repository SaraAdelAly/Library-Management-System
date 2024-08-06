package librarymanagementsystem.controller;

import librarymanagementsystem.dtos.CreatedPatron;
import librarymanagementsystem.dtos.PatronDto;
import librarymanagementsystem.service.PatronService;
import librarymanagementsystem.utils.exceptions.PatronNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    public ResponseEntity<List<CreatedPatron>> getAllPatrons() {
        List<CreatedPatron> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatronDto> getPatron(@PathVariable Integer id) {
        try {
            PatronDto patronDto = patronService.getPatron(id);
            return ResponseEntity.ok(patronDto);
        } catch (PatronNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<CreatedPatron> addPatron(@RequestBody CreatedPatron createdPatron) {
        CreatedPatron addedPatron = patronService.addPatron(createdPatron);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedPatron> updatePatron(@PathVariable Integer id, @RequestBody CreatedPatron createdPatron) {
        try {
            CreatedPatron updatedPatron = patronService.updatePatron(id, createdPatron);
            return ResponseEntity.ok(updatedPatron);
        } catch (PatronNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Integer id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("patron deleted successfully");
        } catch (PatronNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patron not found");
        }
    }

}
