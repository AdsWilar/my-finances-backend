package bo.jads.myfinancesbackend.app.exceptions.files;

public class DirectoryCreationException extends FileException {

    public DirectoryCreationException() {
        super("The directory could not be created.");
    }

}