package unmsm.hospital.sistemaCitas.entity;

import java.util.List;
import java.util.ArrayList;

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

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="users_roles",
			joinColumns={@JoinColumn(name="user_id", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="ID")})
	private List<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Patient> patients = new ArrayList<>();

	@OneToOne(mappedBy = "user")
	private Doctor doctor;

}

