package bo.jads.myfinancesbackend.app.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FileStorageConfig {

    @Value("${file.storage.path.base}")
    private String basePath;

    @Value("${file.storage.path.users}")
    private String usersPath;

}
