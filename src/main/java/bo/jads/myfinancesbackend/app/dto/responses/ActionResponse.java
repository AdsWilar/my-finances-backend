package bo.jads.myfinancesbackend.app.dto.responses;

import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActionResponse {

    private Long id;
    private String name;
    private ActionCode code;

}
