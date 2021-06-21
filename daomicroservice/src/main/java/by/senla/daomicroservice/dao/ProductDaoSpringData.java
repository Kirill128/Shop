package by.senla.daomicroservice.dao;

import by.senla.daomicroservice.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductDaoSpringData extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"category","photo","provider"})
    @Override
    Optional<Product> findById(Long aLong);
}
