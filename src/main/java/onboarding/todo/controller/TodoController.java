package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoDto;
import onboarding.todo.entity.User;
import onboarding.todo.repository.TodoRepository;
import onboarding.todo.repository.UserRepository;
import onboarding.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Token")
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Operation(summary = "Todo 생성")
    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(Principal principal, @RequestBody @Valid TodoDto dto){
        Optional<User> userOp = userRepository.findByEmail(principal.getName());
        System.out.println("principal = " + principal.getName());
        User user = userOp.get();
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(user, dto));
    }

    @Operation(summary = "Todo 조회")
    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodo(Principal principal) {
        Optional<User> userOp = userRepository.findByEmail(principal.getName());
        User user = userOp.get();
        System.out.println("Get Logic is running");
        System.out.println(todoService.getTodoList(user));
        System.out.println();
        todoService.getTodoList(user);
        return ResponseEntity.ok().body(todoService.getTodoList(user));
    }

    @Operation(summary = "Todo 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(Long id) {
        return ResponseEntity.ok().body("Success: Update Todo");
    }
//
//    @Operation(summary = "Todo 삭제")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteTodo(Long id) {
//        return ResponseEntity.ok().body("Success: Delete Todo");
//    }


}
