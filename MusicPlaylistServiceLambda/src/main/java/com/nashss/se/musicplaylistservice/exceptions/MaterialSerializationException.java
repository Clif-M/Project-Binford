package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given Task orgId and taskId is not found
 * in the database.
 */
public class MaterialSerializationException extends RuntimeException {

    private static final long serialVersionUID = -6299311116787620694L;

    /**
     * Exception with no message or cause.
     */
    public MaterialSerializationException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public MaterialSerializationException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public MaterialSerializationException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public MaterialSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
