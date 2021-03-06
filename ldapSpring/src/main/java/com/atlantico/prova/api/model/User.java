package com.atlantico.prova.api.model;

import javax.naming.Name;

import org.springframework.data.domain.Persistable;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Users")
@Entry(base = "ou=users", objectClasses = {"top", "person", "organizationalPerson", "inetOrgPerson"})
public class User implements Persistable<Object>{
        
	@Id
	@JsonIgnore
	private Name id;
	
	@ApiModelProperty(name="cn", example = "John Doe")
	private String cn;
	@ApiModelProperty(name="sn", example = "Doe")
	private String sn;
	@ApiModelProperty(name="uid", example = "john")
	private String uid;
	
	@Transient
	@JsonIgnore
	private boolean newLdap;
	
	public User() {

	}
	
	public User(String cn, String sn, String uid) {
		super();
		this.cn = cn;
		this.sn = sn;
		this.uid = uid;
	}

	public boolean isNewLdap() {
		return newLdap;
	}


	public void setNewLdap(boolean newLdap) {
		this.newLdap = newLdap;
	}


	public Name getId() {
		return id;
	}

	public void setId(Name id) {
		this.id = id;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	@JsonIgnore
	public boolean isNew() {
		return this.isNewLdap();
	}

}
