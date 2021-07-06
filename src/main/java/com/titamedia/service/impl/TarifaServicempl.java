package com.titamedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.model.Tarifa;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.repository.ITarifaRepository;
import com.titamedia.service.ITarifaService;

@Service
public class TarifaServicempl extends CRUDImpl<Tarifa, Integer> implements ITarifaService{

	@Autowired
	private ITarifaRepository tarifaRepo;
	
	@Override
	protected IGenericRepository<Tarifa, Integer> getRepo() {
		return tarifaRepo;
	}
	
	@Override
	public Page<Tarifa> listarPageable(Pageable pageable) throws Exception {
		Page<Tarifa> tarifas = tarifaRepo.listarPageable(pageable);
		
		if(tarifas.isEmpty()) {
			throw new ModeloNotFoundException("No se ecnontraron datos"); 
		}
		return tarifas;
	}

}
