package com.Victor.Zamanillo.Jump2Digital2022.BackEnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
	
	@Id
	private String id;
	private String website;
	private String name;
	private int founded;
	private String size;
	private String locality;
	private String region;
	private String country;
	private String industry;
	private String linkedin_url;
	
}
