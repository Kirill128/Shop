package by.senla.daomicroservice.mystarter.configs;


import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class HIberDialect extends PostgreSQL95Dialect {
    public HIberDialect() {
        super();
        this.registerHibernateType(
                Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}
