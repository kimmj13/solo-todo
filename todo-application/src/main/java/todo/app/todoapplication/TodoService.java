package todo.app.todoapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {

        Todo findTodo = verifyExistTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(findTodo::setTitle);
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(findTodo::setTodo_order);
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(findTodo::setCompleted);

        return todoRepository.save(findTodo);
    }

    private Todo verifyExistTodo(long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        return optionalTodo.orElseThrow(() -> new RuntimeException("존재하지 않습니다."));
    }

    public Todo findTodo(long id) {
        return verifyExistTodo(id);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }


}
