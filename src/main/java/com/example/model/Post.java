package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_tbl")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Column(name = "post_data")
    private String postData;
    @NotNull(message = "enter userid by which post will crested")
    @JsonIgnore
    @ManyToOne
    private User user;

}
