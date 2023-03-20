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
        try {
            return new ResponseEntity<>(new GenericResponse<>("Added contact", service.createContact(request)), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException ex) {
            System.out.println("[addContact] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<ContactDTO>>> showAll() {
        try {
            return new ResponseEntity<>(new GenericResponse<>("List of contacts", service.getAllContacts()), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            System.out.println("[showAll] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ContactDTO>> getContactDetails(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new GenericResponse<>("Contact details", service.getContactDetails(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id"))), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            System.out.println("[getContactDetails] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ContactDTO>> updateContact(@PathVariable("id") Long id,
                                                                     @RequestBody UpdateContactRequest request) {
        try {
            request.setId(id);
            return new ResponseEntity<>(new GenericResponse<>("Contact details", service.updateContact(request)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            System.out.println("[updateContact] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> removeContact(@PathVariable("id") Long id) {
        try {
            RemoveContactRequest request = new RemoveContactRequest();
            request.setId(id);
            service.deleteContact(request);
            return new ResponseEntity<>(new GenericResponse<>("Contact details", null), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            System.out.println("[removeContact] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<ContactDTO>>> searchContacts(@RequestParam("firstName") Optional<String> firstName,
                                                                            @RequestParam("lastName")Optional<String> lastName,
                                                                            @RequestParam("contact") Optional<String> contact) {
        try {
            SearchContactRequest request = new SearchContactRequest();
            request.setContact(contact.orElse(null));
            request.setFirstName(firstName.orElse(null));
            request.setLastName(lastName.orElse(null));
            return new ResponseEntity<>(new GenericResponse<>("List of contacts", service.searchContacts(request)), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            System.out.println("[searchContacts] IllegalArgumentException " + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new GenericResponse<>("Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
