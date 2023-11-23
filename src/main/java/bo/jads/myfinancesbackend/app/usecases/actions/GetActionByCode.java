package bo.jads.myfinancesbackend.app.usecases.actions;

import bo.jads.myfinancesbackend.app.domain.entities.Action;
import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import bo.jads.myfinancesbackend.app.domain.repositories.ActionRepository;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.ActionNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetActionByCode implements BaseUseCase<ActionCode, Action> {

    private final ActionRepository actionRepository;

    @Override
    public Action execute(ActionCode code) throws ActionNotFoundException {
        return actionRepository.findByCode(code).orElseThrow(ActionNotFoundException::new);
    }

}
