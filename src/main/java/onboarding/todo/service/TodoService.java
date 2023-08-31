package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoDto;
import onboarding.todo.entity.Todo;
import onboarding.todo.entity.User;
import onboarding.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDto createTodo(User user, TodoDto todos) {
        Todo toEntity = new Todo(user, todos.getTodo());
        return TodoDto.from(todoRepository.save(toEntity));
    }

    public List<TodoDto> getTodoList(User user) {
        return todoRepository.findAllByUserId(user.getId())
                .stream().map(todo -> new TodoDto(todo.getId(), todo.getTodos())).toList();
    }

    public Todo getTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        return optionalTodo.get();

    }

//    public Todo updateTodo(Long id, Todo updatedTodo) {
//        Todo todo = getTodoById(id);
//        // 여기에서 Todo 수정 로직을 구현하면 됩니다.
//        // 예를 들어, 제목과 내용을 업데이트하는 로직:
//        todo.todos(updatedTodo.getTitle());
//        todo.setDescription(updatedTodo.getDescription());
//        return todoRepository.save(todo);
//    }

    public String delete(Long id) {

        return "SUCCESS: Delete Service!";
    }

}
