package co.com.sofka.crud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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




}
