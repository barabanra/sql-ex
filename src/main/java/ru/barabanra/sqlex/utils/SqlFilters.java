package ru.barabanra.sqlex.utils;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlFilters {

    private final SqlParameterSource params;
    private final String predicate;

    public boolean isEmpty() {
        return StringUtils.isBlank(predicate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final MapSqlParameterSource params = new MapSqlParameterSource();
        private final List<String> predicateBuilder = new ArrayList<>();

        private Builder() {
        }

        public <T> Builder in(String columnName, Collection<T> values) {
            if (CollectionUtils.isNotEmpty(values)) {
                String paramName = columnName + "In";
                predicateBuilder.add(columnName + " IN(" + param(paramName) + ")");
                List<String> valuesString = values.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());
                params.addValue(paramName, valuesString);
            }
            return this;
        }

        public Builder lte(String columnName, Number value) {
            if (null != value) {
                String paramName = columnName + "Lte";
                predicateBuilder.add(columnName + " < " + param(paramName));
                params.addValue(paramName, value);
            }
            return this;
        }

        public SqlFilters build() {
            String predicate = String.join(" AND ", predicateBuilder);
            return new SqlFilters(params, predicate);
        }

        private String param(String columnName) {
            return ":" + columnName;
        }
    }

}