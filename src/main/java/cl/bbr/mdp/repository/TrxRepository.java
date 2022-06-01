package cl.bbr.mdp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.bbr.mdp.entity.EntityTransaction;

@Repository
@Transactional
public interface TrxRepository extends JpaRepository<EntityTransaction, Integer> {
	public abstract ArrayList<EntityTransaction> findAll();
	public abstract ArrayList<EntityTransaction> findByType(String type);

}
