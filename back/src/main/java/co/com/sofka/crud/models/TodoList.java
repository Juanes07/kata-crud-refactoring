package co.com.sofka.crud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_list")
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Debes tener entre 5 y 50 caracteres")
    @Column(name = "name_list", length = 50)
    private String listname;

    @OneToMany(mappedBy = "todoList",
    targetEntity = Todo.class,
    fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Todo> todos;


}
