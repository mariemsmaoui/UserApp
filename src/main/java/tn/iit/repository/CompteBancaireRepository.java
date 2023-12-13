package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.CompteBancaire;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, String> {

}
