package com.mukesh.expenseapp.Repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;



@Entity
@Table(name="users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "user Name cannot be blank")
    @NotNull(message = "User Name cannot be Null")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "user Email cannot be blank")
    @NotNull(message = "User Email cannot be Null")
    @Email(message ="Enter a Valid Email")
    private String email;
    @Column
    @NotBlank(message = "user Password cannot be blank")
    @NotNull(message = "User Password cannot be Null")
    @Size(min=5, message = "Password should be atleast 5 characters")
    private String password;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    private  Long age;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


    public Users() {
    }

    public Users(Long id, String name, String email, String password, Timestamp createdAt, Long age, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.age = age;
        this.updatedAt = updatedAt;
    }



}
