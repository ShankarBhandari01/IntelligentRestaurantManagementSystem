package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import jakarta.validation.constraints.NotNull;

public interface Mapper<E, Req, Res> {
    E toEntity(@NotNull Req req);

    Res toResponse(@NotNull E entity);
}
