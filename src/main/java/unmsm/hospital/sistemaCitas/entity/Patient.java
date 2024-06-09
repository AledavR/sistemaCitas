package unmsm.hospital.sistemaCitas.entity;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 800)
    private String medical_conditions;

    @Column(length = 400)
    private String allergies;
    
    @OneToMany(mappedBy = "patient")
    private Set<MedicalService> medicalServices = new HashSet<>();
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName  = "id")
    private User user;
}
