package org.fastrackit.onlineshopapi.exception;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(message, ErrorCodes.NOT_FOUND);
    }

}


