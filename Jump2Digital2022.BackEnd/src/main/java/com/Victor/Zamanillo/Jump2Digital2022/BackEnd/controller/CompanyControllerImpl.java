package com.Victor.Zamanillo.Jump2Digital2022.BackEnd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.dto.CompanyDTO;
import com.Victor.Zamanillo.Jump2Digital2022.BackEnd.service.CompanyServiceImpl;

@RequestMapping("/companies")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyControllerImpl {

	@Autowired
	private CompanyServiceImpl compService;

	@GetMapping("/")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() { // retorna lista de todas las companies
		
		ResponseEntity<List<CompanyDTO>> responseEntity;

		try {
			List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();

			companiesDTO = compService.getAllCompanies();

			if (companiesDTO.isEmpty()) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
			responseEntity = new ResponseEntity<List<CompanyDTO>>(companiesDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	@GetMapping("/orderByFoundedDate")
	public ResponseEntity<List<CompanyDTO>> getAllCompaniesByFoundedDate() { // retorna lista de todas las companies ordenada por fecha de creacion
		
		ResponseEntity<List<CompanyDTO>> responseEntity;

		try {
			List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();

			companiesDTO = compService.getCompaniesByFoundedDate();

			if (companiesDTO.isEmpty()) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
			responseEntity = new ResponseEntity<List<CompanyDTO>>(companiesDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	@GetMapping("/orderBySize")
	public ResponseEntity<List<CompanyDTO>> getAllCompaniesBySizeAsc() { // retorna lista de todas las companies ordenada por tamaño
		
		ResponseEntity<List<CompanyDTO>> responseEntity;

		try {
			List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();

			companiesDTO = compService.getCompaniesBySizeAsc();

			if (companiesDTO.isEmpty()) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
			responseEntity = new ResponseEntity<List<CompanyDTO>>(companiesDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	@PostMapping("/addList")
	public ResponseEntity<?> addAllCompanies(@RequestBody List<CompanyDTO> companiesDTO) { //Añade lista de compañias
		
		ResponseEntity<String> responseEntity;
		
		try {
			compService.saveAll(companiesDTO);
			responseEntity = new ResponseEntity<>("Se ha creado la lista de compañias", HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Ha habido un problema", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/getData")
	public ResponseEntity<String> getData(){ //Obtiene datos numero compañias segun tipo industria, segun tamaño y segun año de fundacion
		
		ResponseEntity<String> responseEntity;

		try {
			HashMap<String, Integer> CompaniesByIndustryType = new HashMap<String, Integer>();
			HashMap<String, Integer> CompaniesBySize = new HashMap<String, Integer>();
			HashMap<Integer, Integer> CompaniesByFoundedYear=new HashMap<Integer, Integer>();
			

			CompaniesByIndustryType = compService.getCountOfCompaniesByIndustryType();
			CompaniesBySize= compService.getCountOfCompaniesBySize();
			CompaniesByFoundedYear=compService.getCountOfCompaniesByFoundedYear();

			if (CompaniesByIndustryType.isEmpty()&&CompaniesBySize.isEmpty()) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
			responseEntity = new ResponseEntity<String>("CompaniesByIndustryType:\n"+CompaniesByIndustryType.toString()+"\nCompaniesBySize:\n"+CompaniesBySize.toString()+"\nCompaniesByFoundedYear:\n"+CompaniesByFoundedYear.toString(), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			
			responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
}
