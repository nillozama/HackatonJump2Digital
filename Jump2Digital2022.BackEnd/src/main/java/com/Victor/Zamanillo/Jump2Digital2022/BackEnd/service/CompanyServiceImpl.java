package com.Victor.Zamanillo.Jump2Digital2022.BackEnd.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.dto.CompanyDTO;
import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.model.Company;
import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.repository.CompanyRepository;


@Service
public class CompanyServiceImpl {
	
	@Autowired 
	private CompanyRepository compRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CompanyDTO> getAllCompanies(){
		
		List <Company> companies=new ArrayList<Company>();
		compRepository.findAll().forEach(c->companies.add(c));
		List<CompanyDTO> companiesDTO=new ArrayList<CompanyDTO>();
		
		if (!companies.isEmpty()) {

			companies.forEach(p->companiesDTO.add(modelMapper.map(p, CompanyDTO.class)));
		}
		
		return companiesDTO;
	}
	
	public void save(CompanyDTO companyDTO) {
		
		Company company=modelMapper.map(companyDTO, Company.class);

		compRepository.save(company);
	}
	
	public void saveAll(List<CompanyDTO> companiesDTO) {
		
		for(CompanyDTO companyDTO:companiesDTO) {
		
			Company company=modelMapper.map(companyDTO, Company.class);

			compRepository.save(company);
		}
	}
	
	public List<CompanyDTO> getCompaniesByFoundedDate(){
		
		List <Company> companies=new ArrayList<Company>();
		compRepository.orderCompaniesByFoundedDate().forEach(c->companies.add(c));
		List<CompanyDTO> companiesDTO=new ArrayList<CompanyDTO>();
		
		if (!companies.isEmpty()) {

			companies.forEach(p->companiesDTO.add(modelMapper.map(p, CompanyDTO.class)));
		}
		
		return companiesDTO;
	}
	
	public List<CompanyDTO> getCompaniesBySizeAsc(){
		
		List <Company> companies=new ArrayList<Company>();
		
		compRepository.findAll().forEach(c->companies.add(c));
		List<CompanyDTO> companiesDTO=new ArrayList<CompanyDTO>();
		if (!companies.isEmpty()) {

			companies.forEach(p->companiesDTO.add(modelMapper.map(p, CompanyDTO.class)));
			
		}
		
		Collections.sort(companiesDTO,
                (CompanyDTO a, CompanyDTO b) ->

                
		Double.valueOf(a.getSize().replace("-", ".").replace("+", "")).compareTo(Double.valueOf((b.getSize().replace("-", ".").replace("+", "")))));
		

		return companiesDTO;
	}
	
	public HashMap<String, Integer> getCountOfCompaniesByIndustryType(){
		
		HashMap<String, Integer> industryHashMap= new HashMap<String, Integer>();
		List<String> industriesList=new ArrayList<String>();
		int count=0;
		industriesList=getAllCompanies().stream().map(c->c.getIndustry()).distinct().collect(Collectors.toList());
		
		for(String industry:industriesList) {

			count=getNumberOfCompanies(industry);

			industryHashMap.put(industry,count);
		}
		
		return industryHashMap;
	}
	
	public int getNumberOfCompanies(String industry) {
		
		int count=0;
		List<CompanyDTO> companies=new ArrayList<CompanyDTO>();
		getAllCompanies().forEach(c->companies.add(c));
		
		if(industry==null) {
			
			for(CompanyDTO c:companies) {
				
				if(c.getIndustry()==null) {
					count++;
				}
			}
		}
		else {
			
			for(CompanyDTO c:companies) {
				
				if(industry.equals(c.getIndustry())) {
					count++;
				}
			}
		}

		return count;
	}
	
	
	public HashMap<String, Integer> getCountOfCompaniesBySize(){
		
		HashMap<String, Integer> sizeHashMap= new HashMap<String, Integer>();
		List<String> sizeList=new ArrayList<String>();
		int count=0;
		sizeList=getAllCompanies().stream().map(c->c.getSize()).distinct().collect(Collectors.toList());
		
		for(String size:sizeList) {

			count=getNumberOfCompaniesBySize(size);

			sizeHashMap.put(size,count);
		}
		
		return sizeHashMap;
	}
	
	public int getNumberOfCompaniesBySize(String size) {
		
		int count=0;
		List<CompanyDTO> companies=new ArrayList<CompanyDTO>();
		getAllCompanies().forEach(c->companies.add(c));
		
		if(size==null) {
			
			for(CompanyDTO c:companies) {
				
				if(c.getSize()==null) {
					count++;
				}
			}
		}
		else {
			
			for(CompanyDTO c:companies) {
				
				if(size.equals(c.getSize())) {
					count++;
				}
			}
		}

		return count;
	}
	
	public HashMap<Integer, Integer> getCountOfCompaniesByFoundedYear(){
		
		HashMap<Integer, Integer> foundedHashMap= new HashMap<Integer, Integer>();
		List<Integer> foundedYearList=new ArrayList<Integer>();
		int count=0;
		foundedYearList=getAllCompanies().stream().map(c->c.getFounded()).distinct().collect(Collectors.toList());
		
		for(int founded:foundedYearList) {

			count=getNumberOfCompaniesByFoundedYear(founded);

			foundedHashMap.put(founded,count);
		}
		
		return foundedHashMap;
	}
	
	public int getNumberOfCompaniesByFoundedYear(int founded) {
		
		int count=0;
		List<CompanyDTO> companies=new ArrayList<CompanyDTO>();
		getAllCompanies().forEach(c->companies.add(c));
		
		for(CompanyDTO c:companies) {
			
			if(founded==c.getFounded()){
				count++;
			}
		}

		return count;
	}
}
