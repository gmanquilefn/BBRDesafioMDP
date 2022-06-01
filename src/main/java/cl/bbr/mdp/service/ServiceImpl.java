package cl.bbr.mdp.service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.bbr.mdp.entity.EntityTransaction;
import cl.bbr.mdp.repository.TrxRepository;


@Service
public class ServiceImpl {
	
	enum Type
	{
	    DEBITO, CREDITO, CHEQUE, EFECTIVO;
	}
	
	@Autowired
	private TrxRepository trxRepository;

	@Value("${custom.global.commerce}")
	private String commerce;

	@Value("${custom.global.user}")
	private String user;
	

	public EntityTransaction createRamdonTrx () {
		
		EntityTransaction entTrx = new EntityTransaction ();
		Random ran = new Random();
		int amount = ran.nextInt(6000) + 5000;
		entTrx.setType( Type.values()[new Random().nextInt(Type.values().length)].toString());
		entTrx.setAmount(amount);
		entTrx.setCommerce(commerce);
		entTrx.setUser(user);
		entTrx.setDateTrx(LocalDateTime.now());
		trxRepository.save( entTrx );
		
		return entTrx;
		
	}

	public ArrayList<EntityTransaction> getSearch(String type){
		if(type == null){
			return getAllEntTrx();
		}else{
			return getEntTrxByType(type);
		}

	}

	public ArrayList<EntityTransaction> getAllEntTrx(){
		return (ArrayList<EntityTransaction>) trxRepository.findAll();
	}

	public ArrayList<EntityTransaction> getEntTrxByType(String type){
		return (ArrayList<EntityTransaction>) trxRepository.findByType(type);
	}
	
}
