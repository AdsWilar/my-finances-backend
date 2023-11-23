package bo.jads.myfinancesbackend.app.access.interceptor;

import bo.jads.myfinancesbackend.app.access.SessionHolder;
import bo.jads.myfinancesbackend.app.domain.entities.Action;
import bo.jads.myfinancesbackend.app.domain.entities.ActivityLog;
import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import bo.jads.myfinancesbackend.app.domain.entities.enums.ActivityLogResult;
import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.ActionNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.actions.GetActionByCode;
import bo.jads.myfinancesbackend.app.usecases.activitylogs.SaveActivityLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@Component
public class ResourceActionInterceptor implements HandlerInterceptor {

    private final GetActionByCode getActionByCode;
    private final SaveActivityLog saveActivityLog;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            ResourceAction resourceAction = handlerMethod.getMethodAnnotation(ResourceAction.class);
            if (resourceAction != null) {
                setDataInSessionHolder(resourceAction);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView
    ) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        boolean isThereAnException = ex != null;
        Long affectedEntityId = SessionHolder.getAffectedEntityId();
        EntityType affectedEntityType = SessionHolder.getAffectedEntityType();
        Long loggedInUserId = SessionHolder.getLoggedInUserId();
        Long actionId = SessionHolder.getActionId();
        if (affectedEntityId != null && affectedEntityType != null && loggedInUserId != null &&
                actionId != null) {
            ActivityLog activityLog = new ActivityLog();
            activityLog.setExecutionTimestamp(new Timestamp(new Date().getTime()));
            activityLog.setResult(isThereAnException ? ActivityLogResult.FAILED : ActivityLogResult.SUCCESSFUL);
            activityLog.setMessage(isThereAnException ? ex.getMessage() : "");
            activityLog.setAffectedEntityId(affectedEntityId);
            activityLog.setAffectedEntityType(affectedEntityType);
            activityLog.setUserId(loggedInUserId);
            activityLog.setActionId(actionId);
            saveActivityLog.execute(activityLog);
        }
        SessionHolder.clear();
    }

    private void setDataInSessionHolder(ResourceAction resourceAction) throws ActionNotFoundException {
        ActionCode actionCode = resourceAction.action();
        EntityType entityType = resourceAction.entity();
        if (actionCode.equals(ActionCode.NONE) || entityType.equals(EntityType.NONE)) {
            return;
        }
        Action action = getActionByCode.execute(actionCode);
        SessionHolder.setActionId(action.getId());
        SessionHolder.setAffectedEntityType(entityType);
    }
}
