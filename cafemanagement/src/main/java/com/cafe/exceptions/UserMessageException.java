package com.cafe.exceptions;

public class UserMessageException extends RuntimeException{
    public UserMessageException(String message)
    {
        super(message);
    }
}
