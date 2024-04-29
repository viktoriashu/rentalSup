package com.viktoria.rentalSup.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
