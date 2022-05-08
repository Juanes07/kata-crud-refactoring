package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.GroupListDTO;
import co.com.sofka.crud.DTO.TodoDTO;
import co.com.sofka.crud.models.GroupList;
import co.com.sofka.crud.repositories.GroupListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GroupList Service
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class GroupListService {


    @Autowired
    private GroupListRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<GroupListDTO> list() {
        List<GroupListDTO> listDTOS = repository.findAll().stream().map(groupList -> mapper.map(groupList, GroupListDTO.class)).collect(Collectors.toList());
        return listDTOS;
    }

    public GroupListDTO save(GroupListDTO groupDTO) {
        GroupList gruopentity = mapper.map(groupDTO, GroupList.class);

        GroupList groupList = repository.save(gruopentity);

        GroupListDTO groupListDTO = mapper.map(groupList, GroupListDTO.class);

        return groupListDTO;


    }

    public GroupList update(GroupList groupList) {
        return repository.save(groupList);
    }

    public void delete(Long id) {
        repository.delete(get(id));
    }

    public GroupList get(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
