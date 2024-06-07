package unmsm.hospital.sistemaCitas.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "medical_services")
public class MedicalService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String details;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date service_date;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id",nullable = false)
    private MedicalServiceType medicalServiceType;
    
    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id", nullable = false)
    private Specialty specialty;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "service_results",
               joinColumns =
               {@JoinColumn(name = "service_id", referencedColumnName = "id")},
               inverseJoinColumns =
               {@JoinColumn(name = "result_id", referencedColumnName = "id")})
    private Result result;
    
}
