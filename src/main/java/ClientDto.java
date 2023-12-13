import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.iit.entity.Client;
import tn.iit.entity.CompteBancaire;

@Data

public class ClientDto {

	public class Client implements Serializable {

		private Integer id;

		private String nom;

		private String prenom;

		private String email;

		private String motdepasse;

		private boolean enabled;

		private List<CompteBancaire> listcompteBancaires;

	}

}
