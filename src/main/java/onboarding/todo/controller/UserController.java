package onboarding.todo.controller;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.entity.User;
import onboarding.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    //https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<String> join( UserJoinRequest dto) throws Exception{
        String signupResponse = userService.join(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body(signupResponse);

//        return ResponseEntity.ok().body("회원가입이 성공했습니다.");
    }

    @PostMapping(value = "/signin")
    public void signIn(){
    }

}
