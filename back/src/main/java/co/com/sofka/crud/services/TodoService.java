package co.com.sofka.crud.services;

import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.repositories.TodoRepository;
import co.com.sofka.crud.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;
    private Response response = new Response();

    public Iterable<Todo> list(){
        return repository.findAll();
    }

    public String save(Todo todo){
        if(todo.getName().length() > 4 && todo.getName().length() <=25){
            repository.save(todo);
        } else {
            return "ToDo no creado";
        }
        return response.message = "ToDo registrado";
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }

}
