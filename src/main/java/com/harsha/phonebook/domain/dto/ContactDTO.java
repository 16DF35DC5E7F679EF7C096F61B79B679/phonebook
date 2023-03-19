package com.harsha.phonebook.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
    private Long id;
    private String contact;
    private String firstName;
    private String lastName;
}
