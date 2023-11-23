package bo.jads.myfinancesbackend;

import bo.jads.myfinancesbackend.app.configs.FileStorageConfig;
import bo.jads.tokenmanager.core.TokenManager;
import bo.jads.tokenmanager.exceptions.keys.KeysException;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

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

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().timeZone(TimeZone.getDefault()).dateFormat(new StdDateFormat());
    }

}