package bo.jads.myfinancesbackend.app.usecases.activitylogs;

import bo.jads.myfinancesbackend.app.domain.entities.ActivityLog;
import bo.jads.myfinancesbackend.app.domain.repositories.ActivityLogRepository;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SaveActivityLog implements BaseUseCase<ActivityLog, ActivityLog> {

    private final ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog execute(ActivityLog activityLog) {
        return activityLogRepository.saveAndFlush(activityLog);
    }

}
