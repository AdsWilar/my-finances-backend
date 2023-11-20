package bo.jads.myfinancesbackend;

import bo.jads.myfinancesbackend.app.configs.FileStorageConfig;
import bo.jads.tokenmanager.core.TokenManager;
import bo.jads.tokenmanager.exceptions.keys.KeysException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFinancesBackendApplication {

    public MyFinancesBackendApplication(FileStorageConfig fileStorageConfig) throws KeysException {
        TokenManager.getInstance().initialize(
                fileStorageConfig.getPrivateKeyAbsolutePath(),
                fileStorageConfig.getPublicKeyAbsolutePath(),
                false
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(MyFinancesBackendApplication.class, args);
    }

}