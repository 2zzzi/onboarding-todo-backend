package onboarding.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import onboarding.todo.entity.Todo;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private Long id;
    private String todo;

    public static TodoDto from(Todo todo) {
        return new TodoDto(
                todo.getId(),
                todo.getTodos()
        );
    }
}
