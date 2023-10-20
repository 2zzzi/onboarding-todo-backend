package onboarding.todo.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {
    private String email;
    private String password;
   }

