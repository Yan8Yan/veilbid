package com.project.veilbid.exceptions;

public class LotException extends RuntimeException{
    public LotException() {
    }

    public LotException(String message) {
        super(message);
    }

    public LotException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotException(Throwable cause) {
        super(cause);
    }

    public LotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
