package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoDto;
import onboarding.todo.entity.User;
import onboarding.todo.repository.UserRepository;
import onboarding.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Token")
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserRepository userRepository;

    @Operation(summary = "Todo 생성")
    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(Principal principal, @RequestBody @Valid TodoDto dto){
        User user = userRepository.findByEmail(principal.getName()).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(user, dto));
    }

    @Operation(summary = "Todo 조회")
    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodo(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        return ResponseEntity.ok().body(todoService.getTodoList(user));
    }

    @Operation(summary = "Todo 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id,@RequestBody @Valid TodoDto dto) {
        return ResponseEntity.ok().body(todoService.updateTodo(id, dto));
    }
//
    @Operation(summary = "Todo 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id")Long id) {
        this.todoService.delete(id);
        return ResponseEntity.ok().body("todoService.delete(id)");
    }


}
