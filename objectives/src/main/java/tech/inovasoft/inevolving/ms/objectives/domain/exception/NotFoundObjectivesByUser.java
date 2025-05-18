package tech.inovasoft.inevolving.ms.objectives.domain.exception;

public class NotFoundObjectivesByUser extends Exception {
    public NotFoundObjectivesByUser() {
        super("Not found objectives by user!");
    }

    public NotFoundObjectivesByUser(String message) {
        super(message);
    }
}
