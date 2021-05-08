package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    //---------------------------------Users----------------------------------------------------

    @Override
    public GuestProductDto find(long id, Lang lang) {
        return ProductMapper.mapProductToGuestProductDto(this.productDao.find(id),lang);
    }

    @Override
    public List<GuestProductDto> getAllProducts(Lang lang) {
        return ProductMapper.mapProductsToGuestProductDtos(this.productDao.findAll(),lang);


    }
    @Override
    public List<GuestProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize, Lang lang) {
        return ProductMapper.mapProductsToGuestProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize),lang);
    }

    //----------------------------------Admin ---------------------------------------------------


    @Override
    public List<ProductDto> getAllProducts() throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.findAll());
    }

    @Override
    public List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize));
    }
    @Override
    public void update(ProductDto product) throws JsonProcessingException {
        this.productDao.update(ProductMapper.mapProductDtoToProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public ProductDto createProduct(ProductDto product) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.create(ProductMapper.mapProductDtoToProduct(product)));
    }

    @Override
    public List<ProductDto> createProducts(List<ProductDto> productDtos) throws JsonProcessingException {
        List<Product> savedProducts=new ArrayList<>(productDtos.size());
        for(ProductDto productDto : productDtos){
            savedProducts.add(this.productDao.create(ProductMapper.mapProductDtoToProduct(productDto)));
        }
        return ProductMapper.mapProductsToProductDtos(savedProducts);
    }

    @Override
    public ProductDto findFullInfo(long id) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.find(id));
    }

    @Override
    public List<ProductDto> parseXLSOrXlSXFile(MultipartFile file,Lang lang) throws IOException {
        Workbook workbook= (file.getContentType().equals(".xls"))? new HSSFWorkbook(file.getInputStream()):new XSSFWorkbook(file.getInputStream());
        int quantityInStorageCellNum=8;
        int shortDescrCellNum=3;
        int barcodeCellNum=5;
        int priceCellNum=11;
        ObjectMapper objectMapper=new ObjectMapper();
//        Workbook workbook=new XSSFWorkbook(inputStream);
        List<ProductDto> productDtoList=new LinkedList<>();
        Iterator<Sheet> sheetIterator=workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet currentSheet=sheetIterator.next();
            Iterator<Row> rowIterator= currentSheet.rowIterator();

            while(rowIterator.hasNext()){
                Row currentRow=rowIterator.next();
                ProductDto productDto=new ProductDto();

                Map<String,String> shortDescr=new HashMap<>();
                String shortDescrString=currentRow.getCell(shortDescrCellNum).getStringCellValue();
                if(shortDescrString.isEmpty())continue;
                shortDescr.put(lang.value,shortDescrString);

                Long barcode=-1L;
                Double price=-1.0;
                double quantity=0.0;
                try {
                    barcode = (long) (currentRow.getCell(barcodeCellNum).getNumericCellValue());
                    price = currentRow.getCell(priceCellNum).getNumericCellValue();
                    quantity=currentRow.getCell(quantityInStorageCellNum).getNumericCellValue();
                }catch (Exception e){
                    continue;

                }
                productDto.setBarcode(barcode.toString());
                productDto.setShortDescription(objectMapper.writeValueAsString(shortDescr));
                productDto.setAttributes(null);
                productDto.setCategory(null);
                productDto.setQuantityInStorage((int)quantity);
                productDto.setPrice(price);
                productDtoList.add(productDto);
            }
        }
        return productDtoList;
    }


}
