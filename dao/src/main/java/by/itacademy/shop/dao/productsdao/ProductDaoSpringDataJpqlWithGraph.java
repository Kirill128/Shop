package by.itacademy.shop.dao.productsdao;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


//spring : data jpql, entity graph
@Repository
@Profile("spring-data-jpql")
public interface ProductDaoSpringDataJpqlWithGraph extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @Query("FROM Product p WHERE p.id = ?1")
    Optional<Product> findById(Long aLong);

    @Query("FROM Product p ")
    @EntityGraph(value = "main-page-entity-graph")
    Page<Product> findAllProd(Specification<Product> specification, Pageable pageable);

    @Query("FROM Product p ")
    @EntityGraph(attributePaths = {"category","provider","photo"})
    List<Product> findAllProd();

    @Query("UPDATE Product p " +
            "SET p.shortDescription = :shortDescr,p.barcode=:barcd,p.quantityInStorage=:quantity, " +
            "p.price=:price,p.attributes=:attr,p.category=:cat,p.provider=:prov,p.photo=:photo "+
            "WHERE p.id = :id")
    @Modifying
    void save(@Param("id") long id,
                @Param("shortDescr") Map<String,String> shortDescription,
                @Param("barcd")  String barcode,
                @Param("quantity") int quantityInStorage,
                @Param("price") double price,
                @Param("attr") Map<String,String> attributes,
                @Param("cat")  Category category,
                @Param("prov") Provider provider,
                @Param("photo") Photo photo);


    @Query("DELETE FROM Product WHERE id=?1")
    @Modifying
    void deleteById(long id);

}

