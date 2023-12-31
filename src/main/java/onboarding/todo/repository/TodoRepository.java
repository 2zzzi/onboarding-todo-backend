package onboarding.todo.repository;

import onboarding.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long id);

    Optional<Todo> findById(Long id);

    void deleteById(Long id);

}
