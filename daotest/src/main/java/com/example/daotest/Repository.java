package com.example.daotest;

import com.example.daotest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Product,Long> {

}
