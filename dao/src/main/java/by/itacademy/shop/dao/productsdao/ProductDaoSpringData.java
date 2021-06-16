package by.itacademy.shop.dao.productsdao;

import by.itacademy.shop.entities.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


//spring: data & specification & graph ,
@Repository
@Profile("spring-data")
public interface ProductDaoSpringData extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
//    List<Product> findAll(ProductSearchCriteria productSearchCriteria,Pageable pageable);
}
