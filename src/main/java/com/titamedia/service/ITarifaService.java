package com.titamedia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.titamedia.model.Tarifa;

public interface ITarifaService extends ICRUD<Tarifa, Integer>{

	Page<Tarifa> listarPageable(Pageable pageable)throws Exception;
}
