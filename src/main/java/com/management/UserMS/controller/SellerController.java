package com.management.UserMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.dto.BuyerLoginDTO;
import com.management.UserMS.dto.SellerDTO;
import com.management.UserMS.dto.SellerLoginDTO;
import com.management.UserMS.entity.Seller;
import com.management.UserMS.repository.SellerRepository;
import com.management.UserMS.service.SellerService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class SellerController {

	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);	
	@Autowired
	private SellerService sellerService;

	
	
	@PostMapping(value = "seller/register")
	public String sellerRegistration(@RequestBody SellerDTO sellerDTO) throws Exception {

		try {
			logger.info("Seller Registration is being done by " + sellerDTO.getName());
			sellerDTO.setIsActive(true);
			sellerService.sellerRegistration(sellerDTO);
			return "successfull";

		} catch (Exception exception) {
			return "Unsuccessfull";

	}
		
	}
	
	@PostMapping(value = "seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sellerLogin(@RequestBody SellerDTO sellerDTO){
		try {
			sellerService.sellerLogin(sellerDTO);
			return "Login Successfull";
		} catch (Exception e) {
			return " Login unsuccessfull, check your credentials and try again";
		}
	}
	
	@PostMapping(value = "seller/deactivate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sellerDeactivation(@RequestBody SellerDTO sellerDTO){
			try {
				sellerService.sellerDeactivation(sellerDTO);
				return "Successful";

			} catch (Exception e) {
				return " Account Deactivation Unsuccessful. Give correct credentials";
			}
	}
	
	
}