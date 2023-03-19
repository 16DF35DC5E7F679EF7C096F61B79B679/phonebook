package com.harsha.phonebook.repository;

import com.harsha.phonebook.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByNumber(String number);

    List<Contact> findByNumberContains(String keyword);

    List<Contact> findByFirstNameContainsIgnoreCase(String keyword);

    List<Contact> findByLastNameContainsIgnoreCase(String keyword);
}
