package co.com.sofka.crud.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Modelo DTO de ToDo
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    private Long id_todo;

    private String name;

    private boolean completed;

    private Long id_groupList;
}
