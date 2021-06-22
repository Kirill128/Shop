package by.senla.daomicroservice.service.impl;

import by.senla.daomicroservice.dao.ProductDaoSpringData;
import by.senla.daomicroservice.dto.ProductSearchCriteria;
import by.senla.daomicroservice.entities.SortDirection;
import by.senla.daomicroservice.microservices.constants.Lang;
import by.senla.daomicroservice.microservices.dto.admin.AdminProductDto;
import by.senla.daomicroservice.entities.Product;
import by.senla.daomicroservice.service.ProductService;
import by.senla.daomicroservice.specification.ProductSpecification;
import by.senla.daomicroservice.util.mappers.ProductMapper;
import by.senla.daomicroservice.microservices.dto.forall.GuestProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductDaoSpringData productDao;

    public ProductServiceImpl(ProductDaoSpringData productDao) {
        this.productDao = productDao;
    }

    @Override
    public AdminProductDto create(AdminProductDto entity) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(
                this.productDao.save(ProductMapper.mapProductDtoToProduct(entity))
        );
    }

    @Override
    public AdminProductDto findAdmin(long id) throws JsonProcessingException {
        return null;
    }

    @Override
    public GuestProductDto findGuest(long id, Lang lang) throws JsonProcessingException {
        return ProductMapper.mapProductToGuestProductDto(this.productDao.findById(id).get(),lang);
    }

    @Override
    public void update(AdminProductDto entity) throws JsonProcessingException {
        this.productDao.save(ProductMapper.mapProductDtoToProduct(entity));
    }

    @Override
    public void delete(long id) {
        this.productDao.delete( Product.builder().id(id).build());
    }

    @Override
    public List<GuestProductDto> findAllGuest() throws JsonProcessingException {
        return null;
    }

    @Override
    public List<AdminProductDto> findAllAdmin() throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.findAll());
    }

        @Override
        public Page<GuestProductDto> getProductsPageByCriteria (ProductSearchCriteria searchCriteria, Lang lang) throws JsonProcessingException {
            searchCriteria.setPageNum(Math.max(searchCriteria.getPageNum(), 1));
            if (searchCriteria.getPartOfName() != null) {
                searchCriteria.setPartsOfName(Arrays.asList(searchCriteria.getPartOfName().split("\\s+")));
            }
            Sort.Direction sortDirec;
            if(searchCriteria.getSortDirection()==null ||
                    searchCriteria.getSortDirection().equals(SortDirection.DECREASE)){
                sortDirec=Sort.Direction.DESC;
            }else{
                sortDirec=Sort.Direction.ASC;
            }
            Page<Product> products=this.productDao.findAll(
                    Specification.where(ProductSpecification.categoryIdIs(searchCriteria))
                            .and(ProductSpecification.partsOfNameLikeCriteria(searchCriteria)),
                    PageRequest.of(searchCriteria.getPageNum() - 1,
                            searchCriteria.getPageSize(),
                            sortDirec,
                            (searchCriteria.getSortBy() == null) ? "price" : searchCriteria.getSortBy()));
            return new PageImpl<>(ProductMapper.mapProductsToGuestProductDtos(products.toList(),
                                    lang));

            }


}
