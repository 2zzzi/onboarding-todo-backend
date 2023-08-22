package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.entity.User;
import onboarding.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

//https://www.youtube.com/watch?v=pbUHK3Gzgj4&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=40

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(String email, String password) {

        //email 중복 check
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new RuntimeException(email + "는 이미 있습니다.");
                });

        //저장
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

}
