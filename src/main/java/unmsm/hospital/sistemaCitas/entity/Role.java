package unmsm.hospital.sistemaCitas.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor
@Table(name = "roles") 
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy="roles")
	private List<User> users;

}
