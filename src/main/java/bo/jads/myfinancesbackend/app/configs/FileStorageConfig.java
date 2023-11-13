package bo.jads.myfinancesbackend.app.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Getter
@Configuration
public class FileStorageConfig {

    public static final String SEPARATOR = File.separator;

    @Value("${file.storage.path.base}")
    private String basePath;

    @Value("${file.storage.path.users}")
    private String usersPath;

    @Value("${file.storage.path.keys}")
    private String keysPath;

    @Value("${file.storage.path.private-key}")
    private String privateKeyPath;

    @Value("${file.storage.path.public-key}")
    private String publicKeyPath;

    public String getPrivateKeyAbsolutePath() {
        return getKeysAbsolutePath().concat(SEPARATOR).concat(privateKeyPath);
    }

    public String getPublicKeyAbsolutePath() {
        return getKeysAbsolutePath().concat(SEPARATOR).concat(publicKeyPath);
    }

    private String getKeysAbsolutePath() {
        return basePath.concat(File.separator).concat(keysPath);
    }

}
