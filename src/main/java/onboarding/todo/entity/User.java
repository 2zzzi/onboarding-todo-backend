package onboarding.todo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    private String email;
//    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
