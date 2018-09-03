package service;

import clothing.vo.Clothing;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class ClothingServiceTest {

    static ClothingService clothingService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        clothingService = context.getBean(ClothingService.class);
    }

    @Test
    public void getAllClothingForString() {
    }

    @Test
    public void getClothingByPrice() {
    }

    @Test
    public void insertClothing() {
//        Clothing clothing = new Clothing();
//        clothing.setCreateTime(new Date());
//        clothing.setNumber(100);
//        clothing.setPrice(39);
//        clothingService.insertClothing(clothing);
    }

    @Test
    public void insertListClothing() {
    }

    @Test
    public void deleteClothingByPrice() {
    }

    @Test
    public void updateClothingByPrice() {
        clothingService.updateClothingNumberByPrice(10, 39);
    }
}