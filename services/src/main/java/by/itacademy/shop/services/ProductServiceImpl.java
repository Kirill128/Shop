package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
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
    public List<ProductDto> getAllProducts() {
        return ProductMapper.mapProductsToProductDtos(this.productDao.findAll());
    }

    @Override
    public List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) {
        return ProductMapper.mapProductsToProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize));
    }
    @Override
    public void update(ProductDto product) {
        this.productDao.update(ProductMapper.mapProductDtoToProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public ProductDto createProduct(ProductDto product) {
        return ProductMapper.mapProductToProductDto(this.productDao.create(ProductMapper.mapProductDtoToProduct(product)));
    }

    @Override
    public ProductDto findFullInfo(long id) {
        return ProductMapper.mapProductToProductDto(this.productDao.find(id));
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

                Long barcode=-1L;
                Double price=-1.0;
                try {
                    barcode = (long) (currentRow.getCell(5).getNumericCellValue());
                    price = currentRow.getCell(11).getNumericCellValue();
                }catch (Exception e){
                    continue;
                }
                productDto.setBarcode(barcode.toString());
                productDto.setShortDescription(shortDescr);
                productDto.setQuantityInStorage(0);
                productDto.setAttributes(null);
                productDto.setCategory(null);
                productDto.setPrice(price);
                productDtoList.add(productDto);
            }
        }
        return productDtoList;
    }
}
