package com.artical.portal.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Privilege {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    public String getType() {
        return type;
    }
}
