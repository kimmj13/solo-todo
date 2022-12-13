package todo.app.todoapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin
public class TodoController {

    private final TodoMapper mapper;
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoDto.Post post) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(post));

        return new ResponseEntity(mapper.todoToResponseDto(todo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();
        return new ResponseEntity<>(mapper.todosToResponseDto(todos), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
        Todo todo = todoService.findTodo(id);
        return new ResponseEntity<>(mapper.todoToResponseDto(todo), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
                                    @RequestBody TodoDto.Patch patch) {
        patch.setId(id);
        Todo todo = todoService.updateTodo(mapper.todoPatchDtoToTodo(patch));

        return new ResponseEntity<>(mapper.todoToResponseDto(todo), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
