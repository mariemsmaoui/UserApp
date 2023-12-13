package tn.iit.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.iit.entity.Client;
import tn.iit.entity.OperationCompte;
import tn.iit.enums.EtatCompte;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CompteEpargneDto extends CompteBancaireDto {

	private String rib;
	private double solde;
	private Date createdAt;

	private EtatCompte etatCompte;

	private ClientDto clientDto;
	private double decouvertAutorise;


}
