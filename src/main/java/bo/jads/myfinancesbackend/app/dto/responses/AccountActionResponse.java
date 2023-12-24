package bo.jads.myfinancesbackend.app.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountActionResponse {

    private Long id;
    private Long actionId;
    private ActionResponse action;

}
