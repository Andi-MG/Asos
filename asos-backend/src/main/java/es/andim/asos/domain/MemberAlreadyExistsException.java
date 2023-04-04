package es.andim.asos.domain;

public class MemberAlreadyExistsException extends Exception {

    public MemberAlreadyExistsException() {
        super("The new member is already a member");
    }
}
