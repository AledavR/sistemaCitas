package unmsm.hospital.sistemaCitas.entity;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_specialties",
               joinColumns =
               {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
               inverseJoinColumns =
               {@JoinColumn(name = "specialty_id", referencedColumnName = "id")})
    private List<Specialty> specialties = new ArrayList<>();
    
    @OneToMany(mappedBy = "doctor")
    private Set<MedicalService> medicalServices = new HashSet<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    }

