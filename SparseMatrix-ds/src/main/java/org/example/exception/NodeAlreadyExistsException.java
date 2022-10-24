package org.example.exception;

public class NodeAlreadyExistsException extends RuntimeException {

    public NodeAlreadyExistsException(String msg) {
        super(msg);
    }

    public NodeAlreadyExistsException() {
        super("Node Already exists, use 'update' to update the value");
    }

}
