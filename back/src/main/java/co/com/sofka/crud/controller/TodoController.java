package co.com.sofka.crud.controller;

import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.services.TodoService;
import co.com.sofka.crud.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    @GetMapping(value = "api/todos")
    public Iterable<Todo> list(){
        return service.list();
    }
    
    @PostMapping(value = "api/todo")
    public ResponseEntity<?> save(@RequestBody Todo todo){
        return  new ResponseEntity<>(service.save(todo), HttpStatus.CREATED);
    }

    @PutMapping(value = "api/todo")
    public ResponseEntity<?> update(@RequestBody Todo todo){
        if(todo.getId() != null){
            return new ResponseEntity<>(service.save(todo),HttpStatus.OK);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return service.get(id);
    }

}
