package onboarding.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponse {

    private List<TodoDto> todoRequestList;

    public static TodoListResponse from(List<TodoDto> todoRequestList) {
        return new TodoListResponse(todoRequestList);
    }


}
