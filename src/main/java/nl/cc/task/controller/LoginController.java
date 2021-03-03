package nl.cc.task.controller;


import nl.cc.task.model.LoginRequest;
import nl.cc.task.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.login(loginRequest));
    }

    @GetMapping
    public ResponseEntity<List<String>> getToken() {
        return ResponseEntity.ok().body(Arrays.asList(loginService.getToken()));
    }
}
