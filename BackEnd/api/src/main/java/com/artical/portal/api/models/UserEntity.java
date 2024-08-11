package com.artical.portal.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserEntity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Username;
    private int MobileNumber;
    private String Password;
    private String Email;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name= "user_privilege",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id",referencedColumnName = "id")
    )
    private List<Privilege> privileges=new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> Comments=new ArrayList<Comment>();
}
