package by.itacademy.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ToString
@Getter
@AllArgsConstructor
public enum Status  {
    PAID((short) 1),
    WAITING_PAYMENT((short) 2),
    RECEIVED_BY_CLIENT((short) 3),
    IN_PATH((short) 4),
    AVAILABLE_IN_STORAGE((short) 5),
    NOT_AVAILABLE_IN_STORAGE((short) 6);

    private short value;
    private static final Map<Short,Status> LOOKUP;

    static {
        LOOKUP= Collections.unmodifiableMap(Arrays.stream(Status.values())
            .collect(Collectors.toMap(Status::getValue, Function.identity())));
    }
    public static Status lookup(short value){
        return Optional.ofNullable(LOOKUP.get(value)).orElseThrow(()->new IllegalArgumentException("Unknown value "+value));
    }
}
//<changeSet id="4" author="kirill">
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Оплачено"}'/>
//        </insert>
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Ожидается оплата"}'/>
//        </insert>
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Получено"}'/>
//        </insert>
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Товар в пути"}'/>
//        </insert>
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Есть пункте выдачи"}'/>
//        </insert>
//        <insert tableName="status">
//            <column name="description" value='{"RU":"Нет в пункте выдачи"}'/>
//        </insert>
//    </changeSet>
