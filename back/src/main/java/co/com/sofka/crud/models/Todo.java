package co.com.sofka.crud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "completed", nullable = false)
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = TodoList.class,
            optional = false)
    @JoinColumn(name = "todolist_id")
    @JsonBackReference
    private TodoList todoList;

}
