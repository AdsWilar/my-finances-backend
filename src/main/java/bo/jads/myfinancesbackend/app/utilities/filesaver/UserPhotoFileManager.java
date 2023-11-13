package bo.jads.myfinancesbackend.app.utilities.filesaver;

import bo.jads.myfinancesbackend.app.configs.FileStorageConfig;
import bo.jads.myfinancesbackend.app.dto.FileDto;
import bo.jads.myfinancesbackend.app.exceptions.files.DirectoryCreationException;
import bo.jads.myfinancesbackend.app.exceptions.files.FileException;
import bo.jads.myfinancesbackend.app.exceptions.files.FileSaveException;
import bo.jads.myfinancesbackend.app.utilities.Base64Utility;
import bo.jads.myfinancesbackend.app.utilities.IoUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Component
public class UserPhotoFileManager {

    private final FileStorageConfig fileStorageConfig;

    private static final String SEPARATOR = File.separator;

    public String savePhoto(Long userId, FileDto photo) throws FileException {
        String photoRelativePath = getUserPhotoRelativePath(userId);
        return saveFile(photoRelativePath, photo);
    }

    public String getAbsolutePath(String relativePath) {
        return fileStorageConfig.getBasePath().concat(relativePath);
    }

    private String getUserPhotoRelativePath(Long userId) {
        return SEPARATOR.concat(fileStorageConfig.getUsersPath()).concat(SEPARATOR).concat(userId.toString());
    }

    private String saveFile(String relativePath, FileDto file) throws FileException {
        String directoryAbsolutePath = getAbsolutePath(relativePath);
        File directory = new File(directoryAbsolutePath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new DirectoryCreationException();
        }
        byte[] fileBytes = Base64Utility.decodeAsByteArray(file.getBase64());
        String fileLabel = file.getName();
        String fileName = fileLabel.concat(".").concat(file.getExtension());
        String absoluteFilePath = directoryAbsolutePath.concat(SEPARATOR).concat(fileName);
        try {
            IoUtility.saveFile(fileBytes, absoluteFilePath);
        } catch (IOException e) {
            throw new FileSaveException(e);
        }
        return relativePath.concat(SEPARATOR).concat(fileName);
    }

}
