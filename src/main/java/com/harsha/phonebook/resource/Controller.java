package com.harsha.phonebook.resource;

import com.harsha.phonebook.domain.dto.*;
import com.harsha.phonebook.domain.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/contact")
@RequiredArgsConstructor
public class Controller {
    private final ContactService service;

    @PostMapping("")
    public ResponseEntity<GenericResponse<ContactDTO>> addContact(@RequestBody CreateContactRequest request) {
        return new ResponseEntity<>(new GenericResponse<>("Added contact", service.createContact(request)), HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<ContactDTO>>> showAll() {
        return new ResponseEntity<>(new GenericResponse<>("List of contacts", service.getAllContacts()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ContactDTO>> getContactDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new GenericResponse<>("Contact details", service.getContactDetails(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"))), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ContactDTO>> updateContact(@PathVariable("id") Long id,
                                                                     @RequestBody UpdateContactRequest request) {
        request.setId(id);
        return new ResponseEntity<>(new GenericResponse<>("Contact details", service.updateContact(request)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> removeContact(@PathVariable("id") Long id) {
        RemoveContactRequest request = new RemoveContactRequest();
        request.setId(id);
        service.deleteContact(request);
        return new ResponseEntity<>(new GenericResponse<>("Contact details", null), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<ContactDTO>>> searchContacts(@RequestParam("firstName") Optional<String> firstName,
                                                                            @RequestParam("lastName")Optional<String> lastName,
                                                                            @RequestParam("contact") Optional<String> contact) {
        SearchContactRequest request = new SearchContactRequest();
        request.setContact(contact.orElse(null));
        request.setFirstName(firstName.orElse(null));
        request.setLastName(lastName.orElse(null));
        return new ResponseEntity<>(new GenericResponse<>("List of contacts", service.searchContacts(request)), HttpStatus.OK);
    }
}
