package com.flea.flea.specification;

import com.flea.flea.domain.entity.Participator;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParticipatorSpecification {

    public static Specification<Participator> fromFilters(List<String> filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filters == null) return cb.and();
            for (String filter : filters) {
                String[] parts = filter.split(":", 3);
                if (parts.length < 2) continue;
                String field    = parts[0].trim();
                String operator = parts[1].trim().toUpperCase();
                String value    = parts.length == 3 ? parts[2].trim() : null;
                try {
                    switch (operator) {
                        case "IS_NULL"     -> predicates.add(cb.isNull(root.get(field)));
                        case "IS_NOT_NULL" -> predicates.add(cb.isNotNull(root.get(field)));
                        case "EQ"  -> { if (value != null) predicates.add(cb.equal(root.get(field), castValue(root.get(field).getJavaType(), value))); }
                        case "NEQ" -> { if (value != null) predicates.add(cb.notEqual(root.get(field), castValue(root.get(field).getJavaType(), value))); }
                        case "LIKE"-> { if (value != null) predicates.add(cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%")); }
                        case "GT"  -> { if (value != null) predicates.add(cb.gt(root.get(field), (Number) castValue(root.get(field).getJavaType(), value))); }
                        case "GTE" -> { if (value != null) predicates.add(cb.ge(root.get(field), (Number) castValue(root.get(field).getJavaType(), value))); }
                        case "LT"  -> { if (value != null) predicates.add(cb.lt(root.get(field), (Number) castValue(root.get(field).getJavaType(), value))); }
                        case "LTE" -> { if (value != null) predicates.add(cb.le(root.get(field), (Number) castValue(root.get(field).getJavaType(), value))); }
                        default -> { }
                    }
                } catch (IllegalArgumentException ignored) { }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Object castValue(Class<?> type, String value) {
        if (type == BigDecimal.class) return new BigDecimal(value);
        if (type == Integer.class || type == int.class) return Integer.parseInt(value);
        if (type == Long.class    || type == long.class) return Long.parseLong(value);
        if (type == Boolean.class) return Boolean.parseBoolean(value);
        return value;
    }
}
