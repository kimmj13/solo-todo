package todo.app.todoapplication;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoDto.Post post);

    Todo todoPatchDtoToTodo(TodoDto.Patch patch);

    TodoDto.Response todoToResponseDto(Todo todo);

    List<TodoDto.Response> todosToResponseDto(List<Todo> todos);
}
