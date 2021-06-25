package com.example.daotest.converter;


import com.example.daotest.forentity.Status;

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
