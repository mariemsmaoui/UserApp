package tn.iit.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@DiscriminatorValue("CE")
@Data
@EqualsAndHashCode(callSuper = true) // Ajout de cette annotation pour inclure les méthodes de la classe parente
@ToString(callSuper = true) // Ajout de cette annotation pour inclure les méthodes de la classe parente dans toString
public class CompteEpargne extends CompteBancaire {
 
	private static final long serialVersionUID = 1L;
	private double tauxInteret;

	

}
