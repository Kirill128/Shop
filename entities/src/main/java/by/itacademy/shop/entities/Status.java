package by.itacademy.shop.entities;

public enum  Status {
    PAID("Оплачено"),
    PAID_WAIT("Ожидается оплата"),
    GOT("Получено"),
    IN_TRANSIT("Товар в пути"),
    IN_STOCK("Есть в пункте выдачи"),
    NOT_IN_STOCK("Нет в пункте выдачи");

    private String description;
    Status(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
}
