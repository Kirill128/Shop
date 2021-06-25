package by.itacademy.shop;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class Dialect extends H2Dialect{

    public Dialect() {
        super();
        this.registerHibernateType(
                Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}
