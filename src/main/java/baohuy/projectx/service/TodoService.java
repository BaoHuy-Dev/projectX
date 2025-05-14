package baohuy.projectx.service;

import baohuy.projectx.entity.Todo;
import baohuy.projectx.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public Todo handleCreateTodo(Todo todo) {
        Todo createdTodo = this.todoRepository.save(todo);
        return createdTodo;
    }

    public void handleGetTodo() {
//        List<Todo> todos = this.todoRepository.findAll();
//        todos.forEach(System.out::println);
//        Optional<Todo> todoOptional = this.todoRepository.findById(3L);
        Optional<Todo> todoOptional = this.todoRepository.findByUsername("baohuy3");

        if (todoOptional.isPresent()) {
            System.out.println(todoOptional.get().toString());
        }
    }

    public void handleUpdateTodo() {
        Optional<Todo> todoOptional = this.todoRepository.findById(3L);
        if (todoOptional.isPresent()) {
            Todo currentTodo = todoOptional.get();
            currentTodo.setComplete(false);
            currentTodo.setUsername("baohuy33333");
            this.todoRepository.save(currentTodo);
        }
    }

    public void handleDeleteTodo() {
        this.todoRepository.deleteById(6L);
    }
}
