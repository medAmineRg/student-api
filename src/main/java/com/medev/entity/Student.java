package com.medev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    @Length(min = 2, max = 30)
    @NotEmpty
    private String firstName;
    @Column(name = "lastName")
    @Length(min = 2, max = 30)
    @NotEmpty
    private String lastName;
    @Column(name = "phone")
    @Pattern(
            regexp = "^(06|07)\\d{8}$",
            message = "Invalid phone number format. It should start with '06' or '07' followed by 8 digits."
    )
    private String phone;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
