package com.ecommerceJee.demo.domaine;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PanierVo
{
	private Integer id;
	private UserVo user;
	private List<ArticleVo> articles;
	private String username;//atention
	
	public PanierVo(UserVo user,List<ArticleVo> articles) {
		this.user = user;
		this.articles = articles;
	}


}
