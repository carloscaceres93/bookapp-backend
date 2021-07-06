package com.titamedia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.titamedia.model.Usuario;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer>  {
	
	Usuario findOneByUsername(String username);	
	
	@Query("select rol.nombre from Usuario usu join UsuarioRol usr on usu.idUsuario = usr.usuario.idUsuario "
			+ "join usr.rol rol where usu.username = :usuario")
	List<String> buscarRolePorUsuario(@Param("usuario") String usuario);
	
	@Query("FROM Usuario u WHERE u.username = :usuario")
	Optional<Usuario> validarExisteUusario(@Param("usuario") String usuario);
	

	List<Usuario> findByEstado(Boolean estado);
}
