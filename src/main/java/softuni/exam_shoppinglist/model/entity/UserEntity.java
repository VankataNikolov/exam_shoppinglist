package softuni.exam_shoppinglist.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    private String username;

}
