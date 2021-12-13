package com.adeeb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adeeb.demo.dto.WalletsDTO;
import com.adeeb.demo.exception.ResourceNotFoundException;
import com.adeeb.demo.service.WalletService;

@RestController
@RequestMapping("/service/api/v1/wallets")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WalletsDTO>> findAll(){
		return ResponseEntity.ok(walletService.findAllWallets());
	}
	
	@PostMapping
	public ResponseEntity<WalletsDTO> addWallet(@RequestBody WalletsDTO walletsDTO){
		return ResponseEntity.ok(walletService.addNewWallet(walletsDTO));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletsDTO> findById(@RequestBody WalletsDTO walletsDTO,
            @PathVariable Integer id) throws ResourceNotFoundException{
		return ResponseEntity.ok(walletService.findWalletById(id));
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletsDTO> updateById(@RequestBody WalletsDTO walletsDTO) throws ResourceNotFoundException{
		return ResponseEntity.ok(walletService.updateWallet(walletsDTO));
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteById(@PathVariable Integer id) throws ResourceNotFoundException{
		walletService.deleteWalletById(id);
		return ResponseEntity.ok("");
	}
	
}
