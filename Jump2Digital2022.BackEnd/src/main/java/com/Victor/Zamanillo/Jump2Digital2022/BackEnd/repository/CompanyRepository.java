package com.Victor.Zamanillo.Jump2Digital2022.BackEnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{
	
	@Query(value = "SELECT * FROM companies ORDER BY founded DESC", nativeQuery = true)
	public List<Company> orderCompaniesByFoundedDate();
	
	@Query(value = "SELECT * FROM companies ORDER BY size ASC", nativeQuery = true)
	public List<Company> orderCompaniesBySizeAsc();

}
