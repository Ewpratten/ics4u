package studentID;

public class InvalidStudentException extends Exception {
    private static final long serialVersionUID = -6703748645910051276L;

    public InvalidStudentException(String message) {
        super(message);
    }

}