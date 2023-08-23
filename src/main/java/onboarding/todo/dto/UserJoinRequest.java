package onboarding.todo.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class UserJoinRequest {
    private String email;
    private String password;
   }

