package com.adeeb.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adeeb.demo.dto.WalletsDTO;
import com.adeeb.demo.exception.ResourceNotFoundException;
import com.adeeb.demo.model.Wallets;
import com.adeeb.demo.repository.WalletsRepository;

@Component
public class WalletService {

	@Autowired
	private WalletsRepository walletRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<WalletsDTO> findAllWallets(){
		List<Wallets> list = new ArrayList<Wallets>();
		walletRepository.findAll().forEach(list::add);
		return modelMapper.map(list, new TypeToken<List<WalletsDTO>>() {}.getType());
	}
	
	public WalletsDTO addNewWallet(WalletsDTO dto){
		Wallets wallet = modelMapper.map(dto, Wallets.class);
		wallet = walletRepository.save(wallet);
		return modelMapper.map(wallet, WalletsDTO.class);
	}
	
	public WalletsDTO findWalletById(Integer id) throws ResourceNotFoundException{
		Optional<Wallets> wallets = walletRepository.findById(id);
		if(wallets.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return modelMapper.map(wallets.get(), WalletsDTO.class);
	}
	
	public WalletsDTO updateWallet(WalletsDTO dto) throws ResourceNotFoundException{
		if(dto.getId() == null) {
			throw new ResourceNotFoundException();
		}
		Optional<Wallets> wallets = walletRepository.findById(dto.getId());
		if(wallets.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Wallets updateWallet = modelMapper.map(dto, Wallets.class);
		updateWallet = walletRepository.save(updateWallet);
		return modelMapper.map(updateWallet, WalletsDTO.class);
	}
	
	public void deleteWalletById(Integer id){
		walletRepository.deleteById(id);
	}
}
