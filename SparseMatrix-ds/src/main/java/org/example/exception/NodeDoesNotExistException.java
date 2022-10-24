package org.example.exception;

public class NodeDoesNotExistException extends RuntimeException {

    public NodeDoesNotExistException(String msg) {
        super(msg);
    }

    public NodeDoesNotExistException() {
        super("Node does not exist");
    }

}
