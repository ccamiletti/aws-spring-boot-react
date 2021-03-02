package nl.cc.task.service;

import nl.cc.task.model.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String login(LoginRequest loginRequest){
        return "token";
    }

}
