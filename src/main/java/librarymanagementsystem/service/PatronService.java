package librarymanagementsystem.service;

import librarymanagementsystem.dtos.CreatedPatron;
import librarymanagementsystem.dtos.PatronDto;
import librarymanagementsystem.entities.Patron;
import librarymanagementsystem.mappers.PatronMapper;
import librarymanagementsystem.repositories.PatronRepo;
import librarymanagementsystem.utils.exceptions.PatronNotFoundException;
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


    public List<CreatedPatron> getAllPatrons(){
        List <Patron> patrons= patronRepo.findAll();
        return patrons.stream().map(patronMapper::toCreatedPatron).toList();
    }

    public PatronDto getPatron (Integer id){
        Patron patron = patronRepo.findById(id).orElseThrow(()->new PatronNotFoundException(id));
        return patronMapper.toDto(patron);
    }

    public CreatedPatron addPatron (CreatedPatron createdPatron) {
        Patron patron = patronMapper.toEntityFromCreatedPatron(createdPatron);
        Patron savedPatron = patronRepo.save(patron);
        return patronMapper.toCreatedPatron(savedPatron);
    }

    public CreatedPatron updatePatron (Integer id, CreatedPatron createdPatron){
        Patron updatedPatron= patronRepo.findById(id).map(patron -> {
            patron.setName(createdPatron.getName());
            patron.setEmail(createdPatron.getEmail());
            patron.setPhoneNumber(createdPatron.getPhoneNumber());
            patron.setGender(createdPatron.getGender());
            return patronRepo.save(patron);
        }).orElseThrow(() -> new PatronNotFoundException(id));
        return patronMapper.toCreatedPatron(updatedPatron);
    }


    public void deletePatron (Integer id){
        Optional<Patron> patronOptional = patronRepo.findById(id);
        if (patronOptional.isEmpty()) {
            ResponseEntity.notFound().build();
            return;
        }
        Patron patron= patronOptional.get();
        patronRepo.delete(patron);
        ResponseEntity.ok("Patron deleted successfully");
    }


}
