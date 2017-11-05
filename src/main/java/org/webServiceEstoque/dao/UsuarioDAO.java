package org.webServiceEstoque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.webServiceEstoque.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
    private EntityManager manager;
	
	public Usuario loadUserByUsername(String nome){
	    List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.nome = :nome", Usuario.class)
	            .setParameter("nome", nome)
	            .getResultList();
	    System.out.println(usuarios);

	    if(usuarios.isEmpty()){
	        throw new UsernameNotFoundException("O usuário "+ nome +" não foi encontrado");
	    }

	    return usuarios.get(0);
	}



}
