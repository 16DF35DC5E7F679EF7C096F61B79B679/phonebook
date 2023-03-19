package com.harsha.phonebook.domain.dto;

import lombok.Data;

@Data
public class UpdateContactRequest {
    private Long id;
    private String contact;
    private String firstName;
    private String lastName;
}
