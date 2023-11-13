package bo.jads.myfinancesbackend.app.exceptions.files;

public class FileSaveException extends FileException {

    public FileSaveException(Throwable cause) {
        super("An error occurred while trying to save the file.", cause);
    }

}