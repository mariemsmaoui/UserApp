package tn.iit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants.Include;
import tn.iit.enums.EtatCompte;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "TYPE")//par defaut reserve 256 char
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="comptes")

public class CompteBancaire implements Serializable {

	private static final long serialVersionUID = 1L;
	@Include
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment

	private String rib;
    private double solde;
    private Date createdAt;
    
    @Enumerated(EnumType.STRING)
    private EtatCompte etatCompte;
    
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="id_client")
   
	private Client client;
    
	/*public Client(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}*/
    @OneToMany(mappedBy = "compteBancaire",fetch = FetchType.LAZY)
    private List<OperationCompte>  operationComptes;
    

}