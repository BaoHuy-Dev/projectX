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

    public Todo getTodoById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        return todoOptional.isPresent() ? todoOptional.get() : null;
    }

    public Todo handleCreateTodo(Todo todo) {
        Todo createdTodo = this.todoRepository.save(todo);
        return createdTodo;
    }

    public List<Todo> handleGetTodo() {
        return this.todoRepository.findAll();
    }

    public void handleUpdateTodo(Long id, Todo inputTodo) {
        Optional<Todo> todoOptional = this.todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo currentTodo = todoOptional.get();

            currentTodo.setCompleted(inputTodo.isCompleted());
            currentTodo.setUsername(inputTodo.getUsername());

            this.todoRepository.save(currentTodo);
        }
    }

    public void handleDeleteTodo(Long id) {
        this.todoRepository.deleteById(id);
    }
}
