package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.entity.User;
import onboarding.todo.exception.AppException;
import onboarding.todo.exception.ErrorCode;
import onboarding.todo.repository.UserRepository;
import onboarding.todo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

//https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;


    public String join(String email, String password) {

        //email 중복 check
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, email + "는 이미 있습니다.");
                });

        //저장
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();

        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String email, String password) {
        //Email 없음
        User selectedUser =  userRepository.findByEmail(email)
                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //Password 틀림
        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력했습니다");
        }

        //성공 -> 토큰발행

        String token = JwtTokenUtil.createToken(selectedUser.getEmail(), key, expireTimeMs);

        return token;
    }

}
