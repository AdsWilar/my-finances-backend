package bo.jads.myfinancesbackend.app.access;

import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;

public class SessionHolder {

    private static final ThreadLocal<Long> LOGGED_IN_USER_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<Long> ACTION_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<Long> INVOLVED_ENTITY_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<EntityType> INVOLVED_ENTITY_TYPE = new InheritableThreadLocal<>();

    public static Long getLoggedInUserId() {
        return LOGGED_IN_USER_ID.get();
    }

    public static Long getActionId() {
        return ACTION_ID.get();
    }

    public static Long getInvolvedEntityId() {
        return INVOLVED_ENTITY_ID.get();
    }

    public static EntityType getInvolvedEntityType() {
        return INVOLVED_ENTITY_TYPE.get();
    }

    public static void setLoggedInUserId(Long loggedInUserId) {
        LOGGED_IN_USER_ID.set(loggedInUserId);
    }

    public static void setActionId(Long actionId) {
        ACTION_ID.set(actionId);
    }

    public static void setInvolvedEntityId(Long affectedEntityId) {
        INVOLVED_ENTITY_ID.set(affectedEntityId);
    }

    public static void setInvolvedEntityType(EntityType affectedEntityType) {
        INVOLVED_ENTITY_TYPE.set(affectedEntityType);
    }

    public static void clear() {
        LOGGED_IN_USER_ID.remove();
        ACTION_ID.remove();
        INVOLVED_ENTITY_ID.remove();
        INVOLVED_ENTITY_TYPE.remove();
    }

}
