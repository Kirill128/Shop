package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    //---------------------------------CRUD----------------------------------------------------
    @Override
    public ProductDto createProduct(ProductDto product) {
        return ProductMapper.INSTANCE.mapProductDto(this.productDao.create(ProductMapper.INSTANCE.mapProduct(product)));
    }

    @Override
    public ProductDto find(long id) {
        return ProductMapper.INSTANCE.mapProductDto(this.productDao.find(id));
    }

    @Override
    public void update(ProductDto product) {
        this.productDao.update(ProductMapper.INSTANCE.mapProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return ProductMapper.INSTANCE.mapProductDtos(this.productDao.findAll());
    }
    //----------------------------------Special logic---------------------------------------------------
    @Override
    public List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) {
        return ProductMapper.INSTANCE.mapProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize));
    }

    @Override
    public List<ProductDto> parseXLSOrXlSXFile(MultipartFile file, InputStream inputStream) throws IOException {
//        Workbook workbook= (file.getContentType().equals(".xls"))? new HSSFWorkbook(file.getInputStream()):new XSSFWorkbook(file.getInputStream());
        Workbook workbook=new XSSFWorkbook(inputStream);
        List<ProductDto> productDtoList=new LinkedList<>();
        Iterator<Sheet> sheetIterator=workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet currentSheet=sheetIterator.next();
            Iterator<Row> rowIterator= currentSheet.rowIterator();

            while(rowIterator.hasNext()){
                Row currentRow=rowIterator.next();
                ProductDto productDto=new ProductDto();
                Map<String,String> shortDescr=new HashMap<>();
                shortDescr.put("RU",currentRow.getCell(3).getStringCellValue());

                Long id=-1L;
                Double price=-1.0;
                try {
                    id = (long) (currentRow.getCell(5).getNumericCellValue());
                    price = currentRow.getCell(11).getNumericCellValue();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                productDto.setId(id);
                productDto.setShortDescription(shortDescr);
                productDto.setPrice(price);
                productDtoList.add(productDto);
            }
        }
        return productDtoList;
    }
}
