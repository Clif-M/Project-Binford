package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given Task orgId and taskId is not found
 * in the database.
 */
public class TaskSerializationException extends RuntimeException {

    private static final long serialVersionUID = 3257840714584390287L;

    /**
     * Exception with no message or cause.
     */
    public TaskSerializationException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public TaskSerializationException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public TaskSerializationException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public TaskSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
