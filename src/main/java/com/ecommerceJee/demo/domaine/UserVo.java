package com.ecommerceJee.demo.domaine;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserVo
{
	private Integer id;
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private String nouveaupassword;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean accountNonLocked;

	private List<PanierVo> paniers;
    private List<RoleVo> roles ;
    
	public UserVo(String nom,String prenom,String username,String mdp,String nouveau,List<PanierVo> paniers,List<RoleVo> roles) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = mdp;
		this.nouveaupassword = nouveau;
		this.paniers = paniers;
		this.roles = roles;
	}
	
	public UserVo(String nom,String prenom,String username,String mdp,List<PanierVo> paniers,List<RoleVo> roles) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = mdp;
		this.paniers = paniers;
		this.roles = roles;
	}
	
	public UserVo(String nom, String prenom ,String username, String password,List<PanierVo> paniers, List<RoleVo> roles, boolean enabled, boolean 
			accountNonExpired,boolean credentialsNonExpired,boolean accountNonLocked ) {
		this(nom,prenom,username,password,paniers,roles);
		this.enabled=enabled;
		this.accountNonExpired=accountNonExpired;
		this.credentialsNonExpired=true;
		this.accountNonLocked=accountNonLocked;
	}
	
	public UserVo(String nom,String prenom,String pseudo) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = pseudo;
		
	}


}
