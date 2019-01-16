package main.java.io.bhannur.api.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email=:pEmail")
})
@Getter
@Setter
public class User {

    @Id
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

}
