package es.andim.asos.domain;

public class MemberAlreadyExistsException extends Exception {
    public MemberAlreadyExistsException(String message) {
        super(message);
    }
}
