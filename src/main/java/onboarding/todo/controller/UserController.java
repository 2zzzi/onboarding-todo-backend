package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.dto.UserLoginRequest;
import onboarding.todo.entity.User;
import onboarding.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원가입 기능")
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<String> join( UserJoinRequest dto) throws Exception{
        String signupResponse = userService.join(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body(signupResponse);

//        return ResponseEntity.ok().body("회원가입이 성공했습니다.");
    }

    @Operation(summary = "로그인 기능")
    @PostMapping(value = "/signin")
    public ResponseEntity<String> login(UserLoginRequest dto){
        String token = userService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body(token);
    }

}
