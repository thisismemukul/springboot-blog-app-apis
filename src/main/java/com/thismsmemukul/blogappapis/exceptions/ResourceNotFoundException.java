package com.thismsmemukul.blogappapis.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    UUID fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, UUID fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
