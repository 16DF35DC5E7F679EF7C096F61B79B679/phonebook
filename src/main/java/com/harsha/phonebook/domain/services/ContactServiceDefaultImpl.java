package com.harsha.phonebook.domain.services;

import com.harsha.phonebook.domain.dto.*;
import com.harsha.phonebook.domain.model.Contact;
import com.harsha.phonebook.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceDefaultImpl implements ContactService {
    private final ContactRepository repository;

    @Override
    @Transactional
    public ContactDTO createContact(CreateContactRequest request) {
        validateCreateContact(request);
        Optional<Contact> existingContactOptional = repository.findByNumber(request.getNumber());
        if (existingContactOptional.isPresent()) {
            throw new IllegalArgumentException("Contact already exists: " + request.getNumber());
        }
        return mapToDTO.apply(repository.save(mapToModel.apply(request)));
    }

    private void validateCreateContact(CreateContactRequest request) {
        if (request == null) throw new IllegalArgumentException("Invalid request");
        if (StringUtils.isBlank(request.getFirstName()))
            throw new IllegalArgumentException("First name can't be blank");
        if (StringUtils.isBlank(request.getLastName())) throw new IllegalArgumentException("Last name can't be blank");
        if (StringUtils.isBlank(request.getNumber())) throw new IllegalArgumentException("Number can't be blank");
    }

    @Override
    public List<ContactDTO> searchContacts(SearchContactRequest request) {
        List<Contact> contacts;
        if (StringUtils.isNotBlank(request.getContact())) {
            contacts = repository.findByNumberContains(request.getContact());
        } else if (StringUtils.isNotBlank(request.getFirstName())) {
            contacts = repository.findByFirstNameContainsIgnoreCase(request.getFirstName());
        } else if (StringUtils.isNotBlank(request.getLastName())) {
            contacts = repository.findByLastNameContainsIgnoreCase(request.getLastName());
        } else {
            throw new IllegalArgumentException("Please pass at least one valid search param");
        }
        return contacts.stream().map(mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ContactDTO updateContact(UpdateContactRequest request) {
        validateUpdateContact(request);
        Contact contact = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid id. Doesn't exist: " + request.getId()));
        if (StringUtils.isNotBlank(request.getContact()) && !StringUtils.equals(contact.getNumber(), request.getContact())) {
            Optional<Contact> existingContactOptional = repository.findByNumber(request.getContact());
            if (existingContactOptional.isPresent()) {
                throw new IllegalArgumentException("Contact : " + request.getContact() + " already exists");
            }
        }
        if (StringUtils.isNotBlank(request.getContact())) {
            contact.setNumber(request.getContact());
        }
        if (StringUtils.isNotBlank(request.getFirstName())) {
            contact.setFirstName(request.getFirstName());
        }
        if (StringUtils.isNotBlank(request.getLastName())) {
            contact.setLastName(request.getLastName());
        }
        return mapToDTO.apply(repository.save(contact));
    }

    private void validateUpdateContact(UpdateContactRequest request) {
        if (request == null) throw new IllegalArgumentException("Invalid request");
        if (request.getId() == null)
            throw new IllegalArgumentException("Invalid request. Empty id: " + request.getId());
    }

    @Override
    @Transactional
    public void deleteContact(RemoveContactRequest request) {
        validateDeleteContactRequest(request);
        Contact contact = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid request. Doesn't exist: "+ request.getId()));
        repository.delete(contact);
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return repository.findAll().stream().map(mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ContactDTO> getContactDetails(Long id) {
        return repository.findById(id).map(mapToDTO);
    }

    private void validateDeleteContactRequest(RemoveContactRequest request) {
        if (request == null) throw new IllegalArgumentException("Invalid request");
        if (request.getId() == null) throw new IllegalArgumentException("Invalid request. Blank id");
    }

    private static final Function<Contact, ContactDTO> mapToDTO = (contact -> {
        return ContactDTO.builder()
                .id(contact.getId())
                .contact(contact.getNumber())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .build();
    });

    private static final Function<CreateContactRequest, Contact> mapToModel = (request -> {
        Contact contact = new Contact();
        contact.setNumber(request.getNumber());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        return contact;
    });
}
