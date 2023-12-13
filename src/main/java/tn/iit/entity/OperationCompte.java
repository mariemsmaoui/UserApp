package tn.iit.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.iit.enums.TypeOperation;


	@Entity
	@Data @NoArgsConstructor @AllArgsConstructor
	public class OperationCompte {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Date dateOperation;
	    private double montant;
	    
	    @Enumerated(EnumType.STRING)
	    private TypeOperation type;
	    
	    @ManyToOne
	    private CompteBancaire compteBancaire;
	    private String description;
	}

