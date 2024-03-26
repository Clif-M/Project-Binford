package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given Project orgId and projectId is not found
 * in the database.
 */
public class ProjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4951311218130562147L;

    /**
     * Exception with no message or cause.
     */
    public ProjectNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ProjectNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ProjectNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

