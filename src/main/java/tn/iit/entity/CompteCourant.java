package tn.iit.entity;



import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@DiscriminatorValue("Cc")
@Data
@EqualsAndHashCode(callSuper = true) // Ajout de cette annotation pour inclure les méthodes de la classe parente
@ToString(callSuper = true) // Ajout de cette annotation pour inclure les méthodes de la classe parente dans toString
@NoArgsConstructor
@AllArgsConstructor
public class CompteCourant extends CompteBancaire {
   
	private static final long serialVersionUID = 1L;
	private double decouvertAutorise;

	

}
