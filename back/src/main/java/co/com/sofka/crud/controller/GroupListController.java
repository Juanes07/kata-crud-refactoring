package co.com.sofka.crud.controller;

import co.com.sofka.crud.models.GroupList;
import co.com.sofka.crud.services.GroupListService;
import co.com.sofka.crud.utility.Response;
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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupListController {

    @Autowired
    private GroupListService service;

    /**
     * instancia Response para manejo de mensajes / errores
     */

    private Response response = new Response();

    /**
     * Lista de List ToDos
     *
     * @return Iterable
     */

    @GetMapping(value = "/groupLists")
    public Iterable<GroupList> list() {
        return service.list();
    }

    /**
     * Guardar Lista ToDos
     *
     * @param groupList de tipo  GroupList
     * @return ResponseEntity / status Http
     */

    @PostMapping(value = "/groupList")
    public ResponseEntity<?> save(@RequestBody GroupList groupList) {
        response.restart();
        try {
            response.data = service.save(groupList);
            return new ResponseEntity<>(response.data, HttpStatus.OK);
        } catch (Exception exc) {
            response.error = true;
            return new ResponseEntity<>("Lista no guardada", HttpStatus.NOT_FOUND);
        }

    }


    /**
     * Actualizar Lista de ToDos
     *
     * @param groupList de tipo GroupList
     * @return
     */

    @PutMapping(value = "/groupList")
    public GroupList update(@RequestBody GroupList groupList) {
        return service.save(groupList);
    }


    /**
     * Borrar Lista
     *
     * @param id de tipo Long
     */

    @DeleteMapping(value = "/{id}/groupList")
    public void delete(@PathVariable("id") Long id) {
        service.delete(get(id));
    }

    /**
     * obetener una lista ToDo por medio de su ID
     *
     * @param id de tipo Long
     * @return lista ToDo obtenida
     */

    @GetMapping(value = "/{id}/groupList")
    public Long get(@PathVariable("id") Long id) {
        return service.get(id).getId_groupList();
    }

}
