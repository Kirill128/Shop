package by.itacademy.shop.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@UtilityClass
public class ImageUploader {
    private static final String IMAGES_FOLDER_PATH="classpath:static/img/";

    public String updateOrCreateImg(MultipartFile file, String startUrl) throws IOException {
        if(file==null || file.isEmpty())return null;
        File img;
        if(startUrl==null){
            img=saveRandomNameFile(file);
            return img.getPath();
        }
        try{
            img= ResourceUtils.getFile(startUrl);
        }catch (FileNotFoundException e){
            img=saveRandomNameFile(file);
        }
        Path path= Paths.get(img.getPath());
        byte[]bytes= file.getBytes();
        Files.write(path,bytes);
        return img.getPath();
    }

    private File saveRandomNameFile(MultipartFile file) throws FileNotFoundException {
        File newFile;
        URL fileUrl=ResourceUtils.getURL(IMAGES_FOLDER_PATH);
        String fileName=UUID.randomUUID()+file.getOriginalFilename();
        String filePath=new StringBuilder(fileUrl.getPath()).append(fileName).toString();
        newFile=new File(filePath);
        while(newFile.exists()) {
            fileName=UUID.randomUUID()+file.getOriginalFilename();
            filePath=new StringBuilder(fileUrl.getPath()).append(fileName).toString();
            newFile=new File(filePath);
        }
        return newFile;
    }
}
