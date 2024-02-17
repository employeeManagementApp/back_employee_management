package com.javaadr.renderapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_quality")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_quality")
    private Quality quality;

    @Column(name = "level")
    private Double level;

}
