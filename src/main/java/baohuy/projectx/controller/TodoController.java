package baohuy.projectx.controller;

import baohuy.projectx.entity.Todo;
import baohuy.projectx.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create-todo")
    public String create() {
        Todo myTodo = new Todo("baohuy", true);
        Todo newTodo = this.todoService.handleCreateTodo(myTodo);
        return "create todo with id = " + newTodo.getId();
    }

    @GetMapping("/todos")
    public String getTodos() {
        this.todoService.handleGetTodo();
        return "get todos";
    }

    @GetMapping("/update-todo")
    public String updateTodo() {
        this.todoService.handleUpdateTodo();
        return "update todo";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo() {
        this.todoService.handleDeleteTodo();
        return "delete Todo";
    }


}