package com.springdatajparest.exampl.SpringBootDataJpaRest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	@Query(value="select e.name,d.name from Employee e,Department d where d.name=:department")
	Object[] testJPA(@Param("department") String department);
 
}

