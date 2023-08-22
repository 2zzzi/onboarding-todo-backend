package onboarding.todo.dto;

import lombok.*;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String email;
    private String password;

}

