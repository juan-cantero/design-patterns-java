package io.juanqui.creational;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.juanqui.utils.Colors.cyan;
import static io.juanqui.utils.Colors.green;


/**
 * ! Patrón Builder:
 * Es un patrón de diseño creacional que nos permite construir objetos complejos
 * paso a paso.
 *
 * El patrón nos permite producir distintos tipos y representaciones
 * de un objeto empleando el mismo código de construcción.
 *
 * * Es útil cuando necesitamos construir un objeto complejo con muchas partes
 * * y queremos que el proceso de construcción sea independiente de las partes
 * * que lo componen.
 *
 * <a href="https://refactoring.guru/es/design-patterns/builder">...</a>
 */

class QueryBuilder {

    private final String table;
    private String[] fields = new String[]{"*"}; // Por defecto selecciona todos los campos (*)
    private final List<String> conditions = new ArrayList<>();
    private final List<String> orderByFields = new ArrayList<>();
    private Optional<Integer> limitCount = Optional.empty(); // Limit como Optional

    // Enum para direcciones de ordenamiento
    public enum Order {
        ASC, DESC
    }

    // Constructor para inicializar la tabla
    public QueryBuilder(String table) {
        this.table = table;
    }

    public QueryBuilder select(String... fields) {
        if (fields.length > 0) {
            this.fields = fields;
        }
        return this;
    }

    public QueryBuilder where(String condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder orderBy(String field, Order direction) {
        orderByFields.add(field + " " + direction);
        return this;
    }

    public QueryBuilder limit(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Limit must be a non-negative number");
        }
        this.limitCount = Optional.of(count);
        return this;
    }

    public String execute() {
        String selectClause = "select " + String.join(", ", fields);
        String fromClause = "from " + table;
        String whereClause = conditions.isEmpty() ? "" : " where " + String.join(" and ", conditions);
        String orderByClause = orderByFields.isEmpty() ? "" : " order by " + String.join(", ", orderByFields);
        String limitClause = limitCount.map(l -> " limit " + l).orElse("");

        return selectClause + " " + fromClause + whereClause + orderByClause + limitClause + ";";
    }

    public static void main(String[] args) {
        // Ejemplo con límite
        String queryWithLimit = new QueryBuilder("users")
                .select("id", "name", "email")
                .where("age > 18")
                .where("country = 'Cri'")
                .orderBy("name", Order.ASC)
                .limit(10)
                .execute();

        System.out.println(cyan("Consulta con límite:"));
        System.out.println(queryWithLimit);

        // Ejemplo sin límite
        String queryWithoutLimit = new QueryBuilder("products")
                .select("id", "price", "category")
                .where("stock > 0")
                .orderBy("price", Order.DESC)
                .execute();

        System.out.println(green("Consulta sin límite:"));
        System.out.println(queryWithoutLimit);
    }
}