package ua.alaali.auth;

public class AuthVisitorDto {

    private static AuthVisitorDto instance;
    public String email;
    public String pass;
    public boolean auth;

    private AuthVisitorDto() {
    }

    public static AuthVisitorDto getInstance() {
        if (instance == null) {
            synchronized (AuthVisitorDto.class) {
                if (instance == null) {
                    instance = new AuthVisitorDto();
                }
            }
        }
        return instance;
    }
}
