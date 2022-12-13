package todo.app.todoapplication;

import lombok.Getter;
import lombok.Setter;

public class TodoDto {
    @Getter
    @Setter
    public static class Post {
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Setter @Getter
    public static class Patch {
        private long id;
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Getter @Setter
    public static class Response {
        private long id;
        private String title;
        private int todo_order;
        private boolean completed;
    }

}
