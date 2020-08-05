package no.nav.springbootkurs.contact;

import lombok.AllArgsConstructor;
import lombok.val;
import no.nav.springbootkurs.contact.dto.ContactResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        return new ResponseEntity<>(mapList(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        val contact = repository.findById(id);
        if(contact.isPresent()) {
            return new ResponseEntity<>(map(contact.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Contact createNewContact(@RequestBody Contact contact) {
        return repository.save(contact);
    }

    @GetMapping("/test")
    public Contact createTestdata() {
        return repository.save(new Contact(1L, "Bjorn", "Kvernstuen", "Bjørn er en apekatt!"));
    }

    @DeleteMapping("/{id}")
    public Long deleteContact(@PathVariable Long id) {
        repository.deleteById(id);
        return id;
    }

    @PutMapping("/{id}")
    public Contact updateContact(@RequestBody Contact updatedContact, @PathVariable Long id) {
        return repository.findById(id)
                .map(e -> {
                    e.setFirstName(updatedContact.getFirstName());
                    e.setLastName(updatedContact.getLastName());
                    return repository.save(e);
                })
                .orElseGet(() -> {
                    updatedContact.setId(id);
                    return repository.save(updatedContact);
                });
    }

    private ContactResponse map(Contact dao) {
        return ContactResponse.builder()
                .id(dao.getId())
                .firstName(dao.getFirstName())
                .lastName(dao.getLastName())
                .build();
    }

    private List<ContactResponse> mapList(List<Contact> dao) {
        return dao.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
