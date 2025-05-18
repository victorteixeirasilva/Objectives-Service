package tech.inovasoft.inevolving.ms.objectives.domain.exception;

public class NotFoundObjectivesByUserAndStatus extends Exception {
    public NotFoundObjectivesByUserAndStatus() {
        super("Not found objectives by user!");
    }

    public NotFoundObjectivesByUserAndStatus(String message) {
        super(message);
    }
}
