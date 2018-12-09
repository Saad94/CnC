package com.friendos.resources;

class DirectoryNotFoundException extends RuntimeException {

    DirectoryNotFoundException(String message) {
        super(message);
    }
}
