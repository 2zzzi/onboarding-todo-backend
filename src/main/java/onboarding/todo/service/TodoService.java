package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoDto;
import onboarding.todo.entity.Todo;
import onboarding.todo.entity.User;
import onboarding.todo.repository.TodoRepository;
import onboarding.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoDto createTodo(String userEmail, TodoDto todoDto) {
        User user = userRepository.findByEmail(userEmail).get();
        Todo toEntity = new Todo(user, todoDto.getTodo());
        return TodoDto.from(todoRepository.save(toEntity));
    }

    public List<TodoDto> getTodoList(String userEmail) {
        User user = userRepository.findByEmail(userEmail).get();
        return todoRepository.findAllByUserId(user.getId())
                .stream().map(todo -> new TodoDto(todo.getId(), todo.getTodos())).toList();
    }

    @Transactional
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todoEntity = todoRepository.findById(id).get();
        todoEntity.update(todoDto.getTodo());
        return TodoDto.from(todoEntity);
    }

    @Transactional
    public void deleteTodo(Long id) {
        this.todoRepository.deleteById(id);
    }
}
