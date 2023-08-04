package bo.jads.myfinancesbackend.app.exceptions;

public class SaveFileException extends Exception {

    public SaveFileException(Throwable cause) {
        super("An error occurred while trying to save the file", cause);
    }

}