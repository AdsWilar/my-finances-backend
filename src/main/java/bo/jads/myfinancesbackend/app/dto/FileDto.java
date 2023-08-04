package bo.jads.myfinancesbackend.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileDto {

    @NotBlank(message = "File name is required")
    private String name;
    @NotBlank(message = "File base64 is required")
    private String base64;
    @NotBlank(message = "File extension is required")
    private String extension;

}
