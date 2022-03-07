package com.company.books.backend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Usuario;

public interface IUserDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUserName(String userName);
	
	@Query("select user from Usuario user where user.userName = ?1")
	public Usuario findByIdUserNameV2(String userName);

}
