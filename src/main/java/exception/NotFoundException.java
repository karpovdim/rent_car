package exception;


public class NotFoundException extends RuntimeException {
    private final int id;

    public NotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}

