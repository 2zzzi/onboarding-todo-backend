package onboarding.todo.controller;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.entity.User;
import onboarding.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    //https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40
    @PostMapping(value = "/signup")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto){
        return ResponseEntity.ok().body("회원가입이 성공했습니다.");
    }

    @PostMapping(value = "/signin")
    public void signIn(){
    }

}
