package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dao.PhotoDao;
import  by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.dao.nativequeryhelper.NativeQueryStringBuilder;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.utilenum.Lang;
import by.itacademy.shop.utilenum.SortDirection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;


@Repository
@Slf4j
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

    private CategoryDao categoryDao;
    private ProviderDao providerDao;
    private PhotoDao photoDao;

    public ProductDaoImpl( CategoryDao categoryDao, ProviderDao providerDao, PhotoDao photoDao) {
        super(Product.class);
        this.categoryDao = categoryDao;
        this.providerDao = providerDao;
        this.photoDao = photoDao;
    }

    public SimplePage<Product> getProductsPageByCriteriaD(ProductSearchCriteria productSearchCriteria) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq=criteriaBuilder.createQuery(Product.class);
        Root<Product> root=cq.from(Product.class);

        Predicate predicate=getPredicate(productSearchCriteria,root,criteriaBuilder);
        cq.where(predicate);
        setOrder(productSearchCriteria,cq,root,criteriaBuilder);

        TypedQuery<Product> typedQuery=entityManager.createQuery(cq);
        typedQuery.setFirstResult(productSearchCriteria.getPageNum()*productSearchCriteria.getPageSize());
        typedQuery.setMaxResults(productSearchCriteria.getPageSize());

        long employeesCount = getProductsCount(predicate,criteriaBuilder);

        return new SimplePage<>(typedQuery.getResultList(), employeesCount);
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria,
                                   Root<Product> productRoot,
                                   CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(productSearchCriteria.getPartOfName())){
            predicates.add(
                    criteriaBuilder.like(productRoot.get("shortDescription"),
                            "{\"RU\":\"%" + productSearchCriteria.getPartOfName() + "%\"}")
            );
        }
        if(Objects.nonNull(productSearchCriteria.getCategoryId())){
            predicates.add(
                    criteriaBuilder.equal(productRoot.get("category_id"), productSearchCriteria.getCategoryId())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ProductSearchCriteria searchCriteria,
                          CriteriaQuery<Product> criteriaQuery,
                          Root<Product> productRoot,
                          CriteriaBuilder criteriaBuilder) {
        if(Objects.isNull(searchCriteria.getSortDirection()) || Objects.isNull(searchCriteria.getSortBy()))return;

        if(searchCriteria.getSortDirection().equals(SortDirection.INCREASE)){
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(searchCriteria.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(searchCriteria.getSortBy())));
        }
    }

    private long getProductsCount(Predicate predicate,CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    @Override
    public SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria){
        NativeQueryStringBuilder nativeQueryStringBuilder=new NativeQueryStringBuilder()
                .select("*")
                .from("product as p")
                .join("LEFT","category as c","p.category_id=c.id")
                .join("LEFT","provider as pr","p.provider_id=pr.id")
                .join("LEFT","photo as ph","p.photo_id=ph.id")
                .orderBy(productSearchCriteria.getSortBy())
                .limitOffset(productSearchCriteria.getPageNum(),productSearchCriteria.getPageSize());
        String name=this.createNameForFind(productSearchCriteria.getPartsOfName());
        if(!name.equals("%"))
            nativeQueryStringBuilder.where(
                String.format("LOWER(p.short_description ->> '%s' LIKE LOWER(%S))",productSearchCriteria.getLang().value,name)
            );
        Query query= entityManager.createNativeQuery(nativeQueryStringBuilder.build());
//        long allCount=() ? entityManager.createQuery("select count(p.id) from Product p "+wherePart).getFirstResult();
        return new SimplePage<>(castList(query.getResultList()),-1);
    }

    private String createNameForFind(List<String> source){
        StringBuilder searchName=new StringBuilder("%");
        source.forEach((e)-> searchName.append(e).append("%"));
        return searchName.toString();
    }

    private List<Product> castList(List<Object> source) {
        List<Product> result = new ArrayList<>(source.size());
        ObjectMapper objectMapper=new ObjectMapper();
        Iterator<Object> itr = source.iterator();
            while (itr.hasNext()) {
                try{
                    Object[] obj = (Object[]) itr.next();
                    result.add(Product.builder()
                            .id(Long.valueOf((Integer)obj[0]))
                            .shortDescription((objectMapper.readValue(objectMapper.writeValueAsString(obj[1]),new TypeReference<HashMap<String, String>>(){})))
                            .barcode((String)obj[2])
                            .quantityInStorage((Integer) obj[3])
                            .price(((BigDecimal)obj[4]).doubleValue())
                            .attributes((objectMapper.readValue(objectMapper.writeValueAsString(obj[5]),new TypeReference<HashMap<String, String>>(){})))
                            .category((obj[6]!=null)?this.categoryDao.find((Integer)obj[6]):null)
                            .photo((obj[7]!=null)?this.photoDao.find((Integer)obj[7]):null)
                            .provider((obj[8]!=null)?this.providerDao.find((Integer)obj[8]):null)
                            .build()//TODO: MAKE JOIN
                    );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        return result;
    }
}
