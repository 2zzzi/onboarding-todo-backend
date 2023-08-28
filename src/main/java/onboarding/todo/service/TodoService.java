package onboarding.todo.service;

import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoCreateRequest;
import onboarding.todo.dto.TodoGetRequest;
import onboarding.todo.entity.Todo;
import onboarding.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public String create(String todos) {
        //Token을 받아와서 uid를 빼와야할거같은데 생각을 더 해보자잉
        Long uid = 100l;

        //Save
//        Todo todo = Todo.builder()
//                .user(uid)
//                .todos(todos)
//                .status("200")
//                .build();
//
//        todoRepository.save(todo);

        return "SUCCESS: Create Service!";
    }

    public List<Todo> get() {
        //Token으로 유저 아이디를 받아와서 모든 Todo를 List로 만들어줘야할거같음
        return this.todoRepository.findAll();
    }

    public String update(Long id, String todos) {


        return "SUCCESS: Update Service!";
    }

    public String delete(Long id) {

        return "SUCCESS: Delete Service!";
    }

}
