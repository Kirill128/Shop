package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.entities.Photo;
import org.junit.jupiter.api.Test;


class PhotoDaoImplTest {
    @Test
    public void create(){
        PhotoDao photoDao=new PhotoDaoImpl();
        Photo photo= Photo.builder()
                .id(200L)
                .url("test create")
                .build();
        photoDao.create(photo);
    }
    @Test
    public void find(){
        PhotoDao photoDao=new PhotoDaoImpl();
        Photo photo= photoDao.find(200L);
        System.out.println(photo);
    }
    @Test
    public void update() {
        PhotoDao photoDao=new PhotoDaoImpl();
        Photo photo= photoDao.find(200L);
        photo.setUrl("test update");
        photoDao.update(photo);
    }
    @Test
    public void delete(){
        PhotoDao photoDao=new PhotoDaoImpl();
        Photo photo= photoDao.find(200L);
        photoDao.delete(photo);
    }

    @Test
    public void findAll(){
        PhotoDao photoDao=new PhotoDaoImpl();
        photoDao.findAll().stream().forEach(System.out::println);
    }
}