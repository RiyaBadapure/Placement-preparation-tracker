package com.example.demo.exception;

public class TopicNotFoundException extends RuntimeException
{
    public TopicNotFoundException(String message)
    {
        super(message);
    }
}
