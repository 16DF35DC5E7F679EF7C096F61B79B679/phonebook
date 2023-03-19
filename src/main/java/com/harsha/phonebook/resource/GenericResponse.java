package com.harsha.phonebook.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GenericResponse<T> {
    String message;
    T data;
}
