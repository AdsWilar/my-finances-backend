package bo.jads.myfinancesbackend.app.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GeneralResponse<Data> {

    private String title;
    private Boolean status;
    private String message;
    private Data data;

}
