package com.titamedia.service.impl;

import java.util.List;
import java.util.Optional;

import com.titamedia.exception.ModeloNotFoundException;
import com.titamedia.repository.IGenericRepository;
import com.titamedia.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepo();

	@Override
	public T registrar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() throws Exception {
		
		List<T> lstObj = getRepo().findAll();
		
		if(lstObj.isEmpty()) {
			throw new ModeloNotFoundException("NO SE ENCONTRARON ELEMENTOS");
		}
		return lstObj;
	}

	@Override
	public T listarPorId(ID id) throws Exception {
		Optional<T> obj = getRepo().findById(id);
		
		if(!obj.isPresent()) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		return obj.get();
	}

	@Override
	public void eliminar(ID id) throws Exception {
		T obj = this.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("NO SE PUDO ELIMINAR EL ELEMENTO");
		}
		
		getRepo().deleteById(id);
	}

}
