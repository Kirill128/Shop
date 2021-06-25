package com.example.daotest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepositoryTest {
    @Autowired
    private Repository repository;
    @Test
    public void test(){
        repository.findAll().forEach(System.out::println);
    }

}