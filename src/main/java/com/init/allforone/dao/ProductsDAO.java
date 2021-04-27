package com.init.allforone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.allforone.entitys.Product;

public interface ProductsDAO extends JpaRepository<Product, Long>{

}
