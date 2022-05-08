package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.TodoDTO;
import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.repositories.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ToDo Service
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private ModelMapper mapper;

    /**
     * Lista con modelo TodoDTO
     * @return listDto
     */

    public List<TodoDTO> list(){
        List<TodoDTO> listDto = repository.findAll().stream().map(todo -> mapper.map(todo,TodoDTO.class)).collect(Collectors.toList());
        return  listDto;
    }

    /**
     *
     * @param todoDTO
     * @return todoDTO
     */

    public TodoDTO save(TodoDTO todoDTO){
       Todo todoentity = mapper.map(todoDTO, Todo.class);

       Todo todo = repository.save(todoentity);

       TodoDTO todoReturn = mapper.map(todo, TodoDTO.class);

       return  todoReturn;

    }

    public Todo update(Todo todo){
        return repository.save(todo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }


}
