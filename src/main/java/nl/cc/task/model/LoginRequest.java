package nl.cc.task.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {

    private final String userName;
    private final String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
