package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.dto.UserLoginRequest;
import onboarding.todo.entity.User;
import onboarding.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40

@Controller
//@SecurityRequirement(name = "JWT Token")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 기능")
    @PostMapping(value ="/signup")
    public ResponseEntity<String> join(@RequestBody @Valid UserJoinRequest dto){
        String signupResponse = userService.join(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body(signupResponse);
    }

    @Operation(summary = "로그인 기능")
    @PostMapping(value = "/signin")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest dto){
        String token = userService.login(dto.getEmail(), dto.getPassword());
        String json = "{\"access_token\" : \"" + token + "\"}";
        return ResponseEntity.ok().body(json);
    }

}
