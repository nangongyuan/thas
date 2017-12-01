package com.yuan.Exception;

public class SocketException extends Exception{
    public SocketException(String message) {
        super(message);
    }

    public SocketException(String message, Throwable cause) {
        super(message, cause);
    }
}
