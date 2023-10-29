package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    @NotBlank(message = "username is mandatory")
    @Column(name = "user_name",unique = true)
    private String userName;
    @NotBlank(message = "please enter a strong password eg : dgA12#hsd")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "please enter a valid password eg : 2137kjdDFH&&")
    @Column(name = "password")
    private String password;
    @NotNull
    @NotBlank(message = "please enter your fullname")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "email is mandatory")
    @Email(message = "please enter a valid email")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "enter phone-number")
    @Length(min = 10 , max = 10,message = "please enter a 10 digit phone number")
    @Column(name = "phone_Num")
    private String phoneNum;
}
