package tech.inovasoft.inevolving.ms.objectives.domain.exception;

public class DataBaseException extends Exception {
    public DataBaseException(String message) {
        super("Database error: " + message);
    }

    public DataBaseException() {
        super("Database error");
    }
}
