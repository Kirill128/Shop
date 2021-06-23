package by.itacademy.shop.dao.productsdao;

import by.itacademy.shop.api.dto.projections.ProductNativeFullView;
import by.itacademy.shop.entities.Product;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("spring-data-native-queries")
public interface ProductDaoSpringDataNativeQueriesWithProjections
        extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

        @Query(value = "SELECT * FROM product as p WHERE p.id = :id",nativeQuery = true)
        Optional<Product> findById(@Param("id") Long id);

        @Query(value = "SELECT * FROM product p ",nativeQuery = true)
        Page<ProductNativeFullView> findAllProd(Specification<Product> specification, Pageable pageable);

        @Query(value = "SELECT * FROM product p",nativeQuery = true)
        List<ProductNativeFullView> findAllProd();

        @Query(value = "UPDATE product p " +
                "SET p.short_description = :shortDescr,p.barcode=:barcd,p.quantity_in_storage=:quantity, " +
                "p.price=:price,p.attributes=:attr,p.category_id=:cat,p.provider_id=:prov,p.photo_id=:photo "+
                "WHERE p.id = :id",nativeQuery = true)
        @Modifying
        void save(@Param("id") long id,
                  @Param("shortDescr") String shortDescr ,
                  @Param("barcd")  String barcode,
                  @Param("quantity") int quantityInStorage,
                  @Param("price") double price,
                  @Param("attr") String attributes,
                  @Param("cat") Long categoryId,
                  @Param("prov") Long providerId,
                  @Param("photo") Long photoId);

        @Query(value ="INSERT INTO product (short_description,barcode,"+
                "quantity_in_storage,price ,attributes ,category_id ,photo_id ,provider_id) " +
                "VALUES(:shortDescr,:barcd,:quantity,:price,:attr,:cat,:prov,:photo)",nativeQuery = true)
        @Modifying
        void save(@Param("shortDescr") String shortDescr ,
                  @Param("barcd")  String barcode,
                  @Param("quantity") int quantityInStorage,
                  @Param("price") double price,
                  @Param("attr") String attributes,
                  @Param("cat") Long categoryId,
                  @Param("prov") Long providerId,
                  @Param("photo") Long photoId);
//PGobject

        @Query(value = "DELETE FROM product WHERE id=?1",nativeQuery = true)
        @Modifying
        void deleteById(long id);
}
