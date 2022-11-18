package org.hyperskill.recipebook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyperskill.recipebook.service.impl.UserDetailsImpl;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    @NotBlank
    @Email
    @Pattern(regexp = ".+@{1}.+\\..+")
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;

    @JsonIgnore
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Recipe> recipes;

    public User(UserDetailsImpl userDetails) {
        this.id = userDetails.getId();
        this.email = userDetails.getUsername();
        this.password = userDetails.getPassword();
    }
}
