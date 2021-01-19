package com.ecommerceJee.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.model.User;

public interface PanierRepository
extends JpaRepository<Panier, Integer> {
	Optional<Panier> findById(Integer id);
	//List<EmployeeEntity> findByFirstName(String name);

	List<Panier> findByUser(User user);
	//List<Panier> findByUserId(Integer userID);

//	List<Panier> findByUserId(Integer clientID);

}