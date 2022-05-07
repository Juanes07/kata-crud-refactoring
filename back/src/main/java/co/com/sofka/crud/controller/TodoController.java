package co.com.sofka.crud.controller;

import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.services.TodoService;
import co.com.sofka.crud.utility.GetErrors;
import co.com.sofka.crud.utility.Response;
import org.apache.catalina.util.ResourceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


/**
 * GroupList Rest Controller
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;
    private GetErrors getErrors;

    @GetMapping(value = "api/todos")
    public ResponseEntity<Response> list() {
        response.restart();
        try {
            response.data = service.list();
            httpStatus = httpStatus.OK;
        } catch (Exception exc) {
            getErrors.getErrorMessageInternal(exc);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "api/todo")
    public Todo save(@RequestBody Todo todo) {
        return service.save(todo);
    }

    @PutMapping(value = "api/todo")
    public ResponseEntity<?> update(@RequestBody Todo todo) {
        if (todo.getId_todo() != null) {
            return new ResponseEntity<>(service.save(todo), HttpStatus.OK);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id) {
        return service.get(id);
    }

}
