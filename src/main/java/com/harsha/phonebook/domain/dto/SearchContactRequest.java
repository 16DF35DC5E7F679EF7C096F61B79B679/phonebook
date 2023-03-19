package com.harsha.phonebook.domain.dto;

import lombok.Data;

@Data
public class SearchContactRequest {
    private String firstName;
    private String lastName;
    private String fullName;
    private String contact;
}
