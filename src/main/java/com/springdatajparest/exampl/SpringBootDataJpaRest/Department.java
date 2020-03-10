package com.springdatajparest.exampl.SpringBootDataJpaRest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department {

	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private Long id;
	 
	  
	 @Column(name="DEPARTMENT_NAME")
	 private String name;
	 
	 
}
