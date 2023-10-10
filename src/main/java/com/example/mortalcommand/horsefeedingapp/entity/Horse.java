package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "horses")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhorse")
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String guid;

    private String officialName;

    private String nickname;

    private String breed;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "stable_id", nullable = false)
    private Stable stable;


}
