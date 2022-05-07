package co.com.sofka.crud.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;


@Entity
@Setter
@Getter
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue
    private Long id_todo;
    private String name;
    private boolean completed;
    private Long id_groupList;


}
