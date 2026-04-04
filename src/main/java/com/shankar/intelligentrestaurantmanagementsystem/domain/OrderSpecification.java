package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderFilterRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class OrderSpecification {

    public static Specification<Order> filter(OrderFilterRequest filter) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getTableId() != null) {
                predicates.add(cb.equal(root.get("tableId"), filter.getTableId()));
            }

            if (filter.getFromDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        filter.getFromDate()
                ));
            }

            if (filter.getToDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("createdAt"),
                        filter.getToDate()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
