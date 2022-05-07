package co.com.sofka.crud.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Modelo GruopList
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupList {
    @Id
    @GeneratedValue
    private Long id_groupList;

    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_groupList")
    private List<Todo> todos;
}
