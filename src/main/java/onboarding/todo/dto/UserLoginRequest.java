package onboarding.todo.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    private String email;
    private String password;
}
