package bo.jads.myfinancesbackend.app.dto.responses;

import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GeneralResponse<Data> {

    private ResponseTitle title;
    private Boolean status;
    private String message;
    private Data data;

}
