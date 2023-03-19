package com.harsha.phonebook.domain.dto;

import lombok.Data;

@Data
public class CreateContactRequest {
    private String number;
    private String firstName;
    private String lastName;
}
