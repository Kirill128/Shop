package by.itacademy.shop.utils;

import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.utilenum.Lang;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@UtilityClass
public class ExelFilesWorker {

    public  List<AdminProductDto> parseXLSOrXlSXFile(MultipartFile file, Lang lang) throws IOException {
        if(file==null || file.getContentType()==null) {
            return new ArrayList<>();
        }
        Workbook workbook= (file.getContentType().equals(".xls")) ?
                new HSSFWorkbook(file.getInputStream()) :
                new XSSFWorkbook(file.getInputStream());
        int quantityInStorageCellNum=1;
        int shortDescrCellNum=3;
        int barcodeCellNum=5;
        int priceCellNum=11;
        ObjectMapper objectMapper=new ObjectMapper();
        List<AdminProductDto> productDtoList=new LinkedList<>();
        Iterator<Sheet> sheetIterator=workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet currentSheet=sheetIterator.next();
            Iterator<Row> rowIterator= currentSheet.rowIterator();

            while(rowIterator.hasNext()){
                Row currentRow=rowIterator.next();
                AdminProductDto productDto=new AdminProductDto();

                Map<String,String> shortDescr=new HashMap<>();
                String shortDescrString=currentRow.getCell(shortDescrCellNum).getStringCellValue();
                if(shortDescrString.isEmpty()){
                    continue;
                }
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
                productDto.setQuantityInStorage((int)quantity);
                productDto.setPrice(price);
                productDtoList.add(productDto);
            }
        }
        return productDtoList;
    }
}
