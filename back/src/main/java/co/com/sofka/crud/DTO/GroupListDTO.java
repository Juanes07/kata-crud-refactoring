package co.com.sofka.crud.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo DTO del gruopList
 *
 * @author Juan Esteban Velasquez P.
 * @version 1.0.0
 * @since 1.0.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupListDTO {

    private Long id_groupList;

    private String name;

}
