package by.itacademy.shop.entities;

import javax.persistence.Entity;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name="category")
public enum Category {
    LED_LAMP("Светоидиодная лампа"),
    UNIVERSAL_REMOTE("Универсальных пульт"),
    COMPUTER_SOCKETS("Компютерные розетки"),
    COMPUTER_ADAPTERS("Компьютерные адаптеры"),
    PHONE_SOCKETS("Телефонные розетки"),
    PUMP("Насосы"),
    PORTABLE_SPEAKERS("Портативные потоки"),
    WIRED_CHARGERS("Проводная зарядка"),
    WIRELESS_CHARGERS("Беспроводная зарядка"),
    POWER_ADAPTER("Адаптеры питания"),
    WIRELESS_HEADPHONES("Беспроводные наушники"),
    WIRED_HEADPHONES("Проводные наушники"),
    CABLE("Кабеля"),
    USB_HAB("USB хаб"),
    HAIR_DRYER("Фены"),
    FORCEPS("Поршни"),
    IRON("Утюги"),
    BLENDER("Блендеры"),
    INCANDESCENT_LAMP("Лампы накала"),
    HALOGEN_LAMP("Галогеновая лампочка");

    private String description;
    Category(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
}

