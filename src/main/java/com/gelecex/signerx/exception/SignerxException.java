package com.gelecex.signerx.exception;

/**
 * Created by obetron on 17.11.2018
 */
public class SignerxException extends Exception {

    public SignerxException() {
        super();
    }

    public SignerxException(String message) {
        super(message);
    }

    public SignerxException(String message, Throwable cause) {
        super(message, cause);
    }
}
