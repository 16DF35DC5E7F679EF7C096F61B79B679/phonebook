package com.harsha.phonebook.domain.services;

import com.harsha.phonebook.domain.dto.*;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    ContactDTO createContact(CreateContactRequest request);
    List<ContactDTO> searchContacts(SearchContactRequest request);
    ContactDTO updateContact(UpdateContactRequest request);
    void deleteContact(RemoveContactRequest request);

    List<ContactDTO> getAllContacts();

    Optional<ContactDTO> getContactDetails(Long id);
}
