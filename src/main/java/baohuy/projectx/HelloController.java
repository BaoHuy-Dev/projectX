package baohuy.projectx;

import baohuy.projectx.entity.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> hello() {
//        return "Hello World test123";
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/baohuy")
    public  ResponseEntity<Todo> test() {
        Todo test = new Todo("baohuytestgetmapping", false);
//        return test;
        return ResponseEntity.ok().body(test);
    }
}