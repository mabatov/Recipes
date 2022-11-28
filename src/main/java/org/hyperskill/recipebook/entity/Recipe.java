package org.hyperskill.recipebook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "recipes")
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank
    @Column(name = "descriprion")
    private String description;

    @NotNull(message = "Ingredients shouldn't be null")
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> ingredients;

    @NotNull(message = "Directions shouldn't be null")
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> directions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @PrePersist
    @PreUpdate
    void createOrUpdateDate() {
        this.date = LocalDateTime.now();
    }
}
