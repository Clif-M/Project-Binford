package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given Material orgId and materialId is not found
 * in the database.
 */
public class MaterialNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5061761164717022019L;

    public MaterialNotFoundException() {
    }

    public MaterialNotFoundException(String message) {
        super(message);
    }

    public MaterialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaterialNotFoundException(Throwable cause) {
        super(cause);
    }

    public MaterialNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
