package com.springdatajparest.exampl.SpringBootDataJpaRest;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VENDOR")
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VENDOR_ID")
	private Long id;
	@Column(name="VENDOR_NAME")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=Customers.class, cascade=CascadeType.ALL)
	@JoinColumn(name="VENDOR_ID_FK",referencedColumnName="VENDOR_ID")
	private Set<Customers> customers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Customers> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customers> customers) {
		this.customers = customers;
	}
	
	

}
