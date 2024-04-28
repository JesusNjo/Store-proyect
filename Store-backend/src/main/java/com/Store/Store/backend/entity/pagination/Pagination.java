package com.Store.Store.backend.entity.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagination {

    private int limit;
    private int skip;
    private Sort.Direction direction;
    private String[] property;
}
