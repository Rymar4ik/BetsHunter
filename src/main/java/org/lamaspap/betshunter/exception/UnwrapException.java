package org.lamaspap.betshunter.exception;

import jakarta.ws.rs.WebApplicationException;

import java.io.Serial;

public class UnwrapException extends WebApplicationException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean suppressStackTrace;
    private final int code;

    public UnwrapException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.suppressStackTrace = true;
        this.code = 0;
    }

    public UnwrapException(String message, int code) {
        super(message, code);
        this.suppressStackTrace = false;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        if (this.suppressStackTrace) {
            return this;
        }
        return super.fillInStackTrace();
    }
}
