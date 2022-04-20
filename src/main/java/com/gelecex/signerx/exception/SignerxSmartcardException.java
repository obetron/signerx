package com.gelecex.signerx.exception;

/**
 * Created by obetron on 21.12.2018
 */
public class SignerxSmartcardException extends Exception {

    public SignerxSmartcardException() {
        super();
    }

    public SignerxSmartcardException(String message) {
        super(message);
    }

    public SignerxSmartcardException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignerxSmartcardException(Throwable cause) {
        super(cause);
    }
}
