package exception;

public class DaoException extends RuntimeException {
    public DaoException() {
//        super();
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
