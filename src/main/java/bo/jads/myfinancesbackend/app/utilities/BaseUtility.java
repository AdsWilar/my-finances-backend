package bo.jads.myfinancesbackend.app.utilities;

public class BaseUtility {

    private BaseUtility() {
        throw new IllegalStateException("Cannot instantiate this class");
    }

    public static Boolean isNull(Object value) {
        return value == null;
    }

}
