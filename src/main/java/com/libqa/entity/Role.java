package com.libqa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;


/**
 * @author 최준호 (braverokmc79@gmail.com)
     
   @업데이트일 2018. 11. 21. 오후 3:12:24
	
 */
@Data
@Entity
@Table(name = "role")
public class Role{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;


}
