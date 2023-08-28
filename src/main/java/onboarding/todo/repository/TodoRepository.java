package onboarding.todo.repository;

import onboarding.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTodos(String todos);

}
