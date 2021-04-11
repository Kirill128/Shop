package by.itacademy.shop.converter;

import by.itacademy.shop.entities.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status,Short> {
    @Override
    public Short convertToDatabaseColumn(Status source) {
        return source.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Short source) {
        return Status.lookup(source);
    }
}
