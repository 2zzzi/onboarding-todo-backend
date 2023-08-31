package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoDto;
import onboarding.todo.entity.Todo;
import onboarding.todo.entity.User;
import onboarding.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public TodoDto updateTodo(Long id, TodoDto dto) {
        Todo todoEntity = todoRepository.findById(id).get();
        todoEntity.Update(dto.getTodo());
        this.todoRepository.save(todoEntity);
        return TodoDto.from(todoEntity);
    }

    @Transactional
    public void delete(Long id) {
        Todo todoEntity = todoRepository.findById(id).get();
        this.todoRepository.delete(todoEntity);
    }
}
