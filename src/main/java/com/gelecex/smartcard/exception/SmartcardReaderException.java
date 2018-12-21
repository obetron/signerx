package com.gelecex.smartcard.exception;

/**
 * Created by obetron on 21.12.2018
 */
public class SmartcardReaderException extends Exception {

    public SmartcardReaderException() {
        super();
    }

    public SmartcardReaderException(String message) {
        super(message);
    }

    public SmartcardReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartcardReaderException(Throwable cause) {
        super(cause);
    }
}
