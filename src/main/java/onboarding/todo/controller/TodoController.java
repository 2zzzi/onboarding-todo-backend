package onboarding.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import onboarding.todo.dto.TodoCreateRequest;
import onboarding.todo.dto.TodoDeleteRequest;
import onboarding.todo.dto.TodoGetRequest;
import onboarding.todo.dto.TodoUpdateRequest;
import onboarding.todo.entity.Todo;
import onboarding.todo.repository.TodoRepository;
import onboarding.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Token")
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoRepository todoRepository;

    @Operation(summary = "Todo 생성")
    @PostMapping("")
    public ResponseEntity<String> createTodo(Authentication authentication){

        return ResponseEntity.ok().body(authentication.getName() + "Success: Create Todo");
    }

    @Operation(summary = "Todo 조회")
    @GetMapping("")
    public ResponseEntity<String> getTodo(Model model) {
        List<Todo> todos = this.todoService.get();
        model.addAttribute("todo", todos);
        return ResponseEntity.ok().body("Success: Get Todo");
    }

    @Operation(summary = "Todo 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") Long id) {

        return ResponseEntity.ok().body("Success: Update Todo");
    }

    @Operation(summary = "Todo 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDeleteRequest dto) {

        return ResponseEntity.ok().body("Success: Delete Todo");
    }


}
