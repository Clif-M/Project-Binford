package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given Task orgId and taskId is not found
 * in the database.
 */
public class OrganizationNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -4404952595213846489L;

    /**
     * Exception with no message or cause.
     */
    public OrganizationNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public OrganizationNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public OrganizationNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public OrganizationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
