package bo.jads.myfinancesbackend.app.access;

import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;

public class SessionHolder {

    private static SessionHolder sessionHolder;
    private final ThreadLocal<UserResponse> loggedInUser;

    private SessionHolder() {
        loggedInUser = new InheritableThreadLocal<>();
    }

    public static SessionHolder getInstance() {
        if (sessionHolder == null) {
            sessionHolder = new SessionHolder();
        }
        return sessionHolder;
    }

    public UserResponse getLoggedInUser() {
        return loggedInUser.get();
    }

    public void setLoggedInUser(UserResponse userResponse) {
        loggedInUser.set(userResponse);
    }

    public void clear() {
        loggedInUser.remove();
    }

}
