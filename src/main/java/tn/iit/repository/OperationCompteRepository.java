package tn.iit.repository;

import org.springframework.stereotype.Repository;

import tn.iit.dto.OperationCompteDto;
import tn.iit.entity.OperationCompte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OperationCompteRepository extends JpaRepository<OperationCompte, Long> {
    public List<OperationCompte> findByCompteBancaireRib(String rib);
}

