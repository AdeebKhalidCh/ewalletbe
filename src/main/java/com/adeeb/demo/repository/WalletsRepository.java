package com.adeeb.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.adeeb.demo.model.Wallets;

public interface WalletsRepository extends PagingAndSortingRepository<Wallets, Integer>{

}
