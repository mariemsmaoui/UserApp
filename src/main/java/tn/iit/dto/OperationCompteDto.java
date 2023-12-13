package tn.iit.dto;

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
import tn.iit.entity.CompteBancaire;
import tn.iit.entity.OperationCompte;
import tn.iit.enums.TypeOperation;

@Data
public class OperationCompteDto {
	private Long id;
	private Date dateOperation;
	private double montant;

	private TypeOperation type;
	private String description;
}
