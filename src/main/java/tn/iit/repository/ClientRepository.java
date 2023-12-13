package tn.iit.repository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.iit.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client ,Integer>{
	 @Query("select c from Client c where c.nom like :kw")
	    List<Client> searchClients(@Param("kw") String keyword);

}
