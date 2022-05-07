package co.com.sofka.crud.controller;

import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.services.TodoService;
import co.com.sofka.crud.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


/**
 * Todo Rest Controller
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService service;
    /**
     * instancia Response para manejo de mensajes / errores
     */

    private Response response = new Response();

    /**
     * Lista de ToDos
     *
     * @return Iterable Lista de ToDo
     */

    @CrossOrigin
    @GetMapping(value = "/todos")
    public Iterable<Todo> list() {
        return service.list();
    }

    /**
     * Guardar ToDo
     *
     * @param todo
     * @return ResponseEntity /  status Http
     */

    @PostMapping(value = "/todo")
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        response.restart();
        try {
            response.data = service.save(todo);
            return new ResponseEntity<>(response.data, HttpStatus.CREATED);
        } catch (Exception exc) {
            response.error = true;
            return new ResponseEntity<>("ToDo no guardado", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualizar ToDo
     *
     * @param todo
     * @return todo Actualizado o en caso de error una excepcion sin interrupcion
     */

    @PutMapping(value = "/todo")
    public Todo update(@RequestBody Todo todo) {
        if (todo.getId_todo() != null) {
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    /**
     * Borrar ToDo
     *
     * @param id de tipo Long
     */

    @DeleteMapping(value = "/{id}/todo")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    /**
     * Obtener Todo por medio de su ID
     *
     * @param id tipo Long
     * @return ToDo obtenido
     */

    @GetMapping(value = "/{id}/todo")
    public Todo get(@PathVariable("id") Long id) {
        return service.get(id);
    }

}
