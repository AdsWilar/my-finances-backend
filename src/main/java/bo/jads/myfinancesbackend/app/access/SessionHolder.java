package bo.jads.myfinancesbackend.app.access;

import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;

public class SessionHolder {

    private static final ThreadLocal<Long> LOGGED_IN_USER_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<Long> ACTION_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<Long> AFFECTED_ENTITY_ID = new InheritableThreadLocal<>();
    private static final ThreadLocal<EntityType> AFFECTED_ENTITY_TYPE = new InheritableThreadLocal<>();

    public static Long getLoggedInUserId() {
        return LOGGED_IN_USER_ID.get();
    }

    public static Long getActionId() {
        return ACTION_ID.get();
    }

    public static Long getAffectedEntityId() {
        return AFFECTED_ENTITY_ID.get();
    }

    public static EntityType getAffectedEntityType() {
        return AFFECTED_ENTITY_TYPE.get();
    }

    public static void setLoggedInUserId(Long loggedInUserId) {
        LOGGED_IN_USER_ID.set(loggedInUserId);
    }

    public static void setActionId(Long actionId) {
        ACTION_ID.set(actionId);
    }

    public static void setAffectedEntityId(Long affectedEntityId) {
        AFFECTED_ENTITY_ID.set(affectedEntityId);
    }

    public static void setAffectedEntityType(EntityType affectedEntityType) {
        AFFECTED_ENTITY_TYPE.set(affectedEntityType);
    }

    public static void clear() {
        LOGGED_IN_USER_ID.remove();
        ACTION_ID.remove();
        AFFECTED_ENTITY_ID.remove();
        AFFECTED_ENTITY_TYPE.remove();
    }

}
