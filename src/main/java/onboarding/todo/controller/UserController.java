package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.dto.UserLoginRequest;
import onboarding.todo.dto.UserLoginResponse;
import onboarding.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 기능")
    @PostMapping(value ="/signup")
    public ResponseEntity<String> join(@RequestBody @Valid UserJoinRequest userJoinRequest){
        String signupResponse = userService.join(userJoinRequest.getEmail(), userJoinRequest.getPassword());
        return ResponseEntity.ok().body(signupResponse);
    }

    @Operation(summary = "로그인 기능")
    @PostMapping(value = "/signin")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        return ResponseEntity.ok().body(userLoginResponse);
    }

}
