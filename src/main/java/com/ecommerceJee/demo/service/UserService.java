package com.ecommerceJee.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerceJee.demo.domaine.PanierConverter;
import com.ecommerceJee.demo.domaine.RoleConverter;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.repository.PanierRepository;
import com.ecommerceJee.demo.repository.RoleRepository;
import com.ecommerceJee.demo.repository.UserRepository;

@Service("userService")
@Transactional
public class UserService implements IUserService
{
	 @Autowired
	    UserRepository repository;
	 @Autowired
		private RoleRepository roleRepository;
	 @Autowired
		private PanierRepository panierRepository;
	 
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		public UserService(UserRepository userRepository, RoleRepository roleRepository,
				BCryptPasswordEncoder bCryptPasswordEncoder) {
			this.repository = userRepository;
//			this.roleRepository = roleRepository;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		}
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = repository.findByUsername(username); 
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
					user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), getAuthorities(user.getRoles()));
		}

		private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
			List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
			for (Role r : roles) {
				springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getLibelle()));
			}
			return springSecurityAuthorities;
		}
		
	 public List<UserVo> getAllUsers()
	    {
	        List<UserVo> userList = UserConverter.toVoList(repository.findAll());
	         
	        if(userList.size() > 0) {
	            return userList;
	        } else {
	            return new ArrayList<UserVo>();
	        }
	    }
//	 public User getUserById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<User> user = repository.findById(id);
//	         
//	        if(user.isPresent()) {
//	            return user.get();
//	        } else {
//	            throw new RecordNotFoundException("No User record exist for given id");
//	        }
//	    }
	 
	   public UserVo getUserById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return UserConverter.toVo(repository.getOne(id));
		}
	 
	 public UserVo createOrUpdateUser(UserVo entity) throws RecordNotFoundException
	    {
	        Optional<User> user = repository.findById(entity.getId());
	         
	     
	        if(user.isPresent())
	        {
	            User newEntity = user.get();
	            newEntity.setNom(entity.getNom());
	            newEntity.setPrenom(entity.getPrenom());
	            newEntity.setUsername(entity.getUsername());
	            newEntity.setRoles(RoleConverter.toBoList(entity.getRoles()));
	            newEntity.setPaniers(PanierConverter.toBoList(entity.getPaniers()));
//	 	       newEntity.setPassword(entity.getPassword());


		       newEntity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));

	 
	            newEntity = repository.save(newEntity);
	             
	            return entity;
	        } else {
	            repository.save(UserConverter.toBo(entity));
	            return entity;
	        }
	    }
	 
	 
	 public List<UserVo> getAllUsers(int pageId, int size) {
			Page<User> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return UserConverter.toVoList(result.getContent());
		}
	 
	 public List<UserVo> findByNom(String libelle) {
			List<User> list = repository.findByNom(libelle);
			return UserConverter.toVoList(list);
		}
	 
	 public UserVo findByUsername(String libelle) {
			User list = repository.findByUsername(libelle);
			return UserConverter.toVo(list);
		}
	 
	 public String findByUsername2(String libelle) {
			User list = repository.findByUsername(libelle);
			return list.getUsername();
	 }
		
	 
	 public List<UserVo> sortBy(String fieldName) {
		 List<User>  list =repository.findAll(Sort.by(fieldName));
			return UserConverter.toVoList(list);
	}
	 
		 
	 @Transactional
	    public User save(User entity) 
	    {
	       entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			List<Role> roles=new ArrayList<Role>();
		
			if(entity.getPaniers()==null) {
				List<Panier> paniers=new ArrayList<Panier>();
				Panier panier=new Panier();	
				panier.setUser(entity);
				//panierRepository.save(panier);
				paniers.add(panier);
				entity.setPaniers(paniers);
				System.out.println("PANIER/////////////////////////////////"+paniers);
				}
			
			
			
			if(entity.getRoles()==null) {
			roles=roleRepository.findByLibelle("CLIENT");	
			entity.setRoles(roles);
			}
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA:"+entity);
			
			User nouveau=repository.save(entity);
			System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO:"+nouveau);
	        return nouveau;     	        
	    }
	 

	  public void deleteUserById(Integer id) throws RecordNotFoundException
	    {
	        Optional<User> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No User record exist for given id");
	        }
	    }
	  
	  @Transactional
		public void delete(Integer id) {
			repository.deleteById(id);
		}
	  
	 @Transactional
		public void deleteAll() {
			repository.deleteAll();
		}
}