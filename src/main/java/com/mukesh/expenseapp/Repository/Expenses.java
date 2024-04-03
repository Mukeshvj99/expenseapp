package com.mukesh.expenseapp.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name="expenses")
@Data
@NoArgsConstructor
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="expense_name")
    @NotNull(message = "Expense Name Cannot be null")
    @NotBlank(message="Expense Name Cannot be blank")
    @Size(min = 3,message="Expense name should have atleast 3 characters")
    private String name;

    @Column
    private String description;
    @Column(name="expense_amount")
    @NotNull(message ="Expense amount cannot be empty")
    private float amount;

    @Column
    @NotNull(message="Category cannot be empty")
    @NotBlank(message = "Category cannot be null")
    private String category;
    @Column
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="users_id")
    @JsonIgnore
    private Users user;

    public Expenses(Long id, String name, String description, float amount, String category, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }




}
