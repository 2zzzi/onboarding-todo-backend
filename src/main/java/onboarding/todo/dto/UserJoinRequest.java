package onboarding.todo.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@Data
public class UserJoinRequest {
    private String email;
    private String password;

}

