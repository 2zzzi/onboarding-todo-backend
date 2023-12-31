package onboarding.todo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String todos;

    public Todo(User user, String todos) {
        this.user = user;
        this.todos = todos;
    }

    public void update(String todos) {
        this.todos = todos;
    }

}
