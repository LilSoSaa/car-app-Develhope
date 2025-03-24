package com.example.car_app;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modelName", nullable = false)
    private String modelName;

    @Enumerated(EnumType.STRING)
    @Column(name = "carType", nullable = false)
    private CarType type;
}
