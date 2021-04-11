package by.itacademy.shop.dao;

import by.itacademy.shop.entities.Status;
import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void findStatus(){
        Status status=Status.lookup((short) 1);
        System.out.printf("Status 1: " + status);
    }
}
