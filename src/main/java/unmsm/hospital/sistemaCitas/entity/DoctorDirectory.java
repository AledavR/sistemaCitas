package unmsm.hospital.sistemaCitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "doctor_directory")
public class DoctorDirectory {
    
    @Id
    @Column(name = "doctor_id")
    private Long id;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String address;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
}
