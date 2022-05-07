package co.com.sofka.crud.services;

import co.com.sofka.crud.models.GroupList;
import co.com.sofka.crud.repositories.GroupListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupListService {


    @Autowired
    private GroupListRepository repository;

    public Iterable<GroupList> list(){
        return repository.findAll();
    }

    public GroupList save(GroupList groupList){
        return repository.save(groupList);
    }

    public GroupList update(GroupList groupList){
        return repository.save(groupList);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public GroupList get(Long id){
        return repository.findById(id).orElseThrow();
    }

}
