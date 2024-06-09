package unmsm.hospital.sistemaCitas.entity;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 60)
    private String names;

    @Column(nullable = false, length = 60)
    private String lastnames;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="users_roles",
               joinColumns={@JoinColumn(name="user_id", referencedColumnName="ID")},
               inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();
    
    @OneToOne(mappedBy = "user")
    private Patient patient;
    
    @OneToOne(mappedBy = "user")
    private Doctor doctor;

}

