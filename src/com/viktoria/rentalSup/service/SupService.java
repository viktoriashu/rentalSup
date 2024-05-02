package com.viktoria.rentalSup.service;

import com.viktoria.rentalSup.dao.impl.SupDao;
import com.viktoria.rentalSup.dto.SupDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SupService {
    private static final SupService INSTANCE = new SupService();

    public static SupService getInstance() {
        return INSTANCE;
    }

    ;
    private final SupDao supDao = SupDao.getInstance();

    public List<SupDto> findAll() {
        return supDao.findAll().stream()
                .map(sup -> SupDto.builder()
                        .id(sup.getId())
                        .description(
                                """
                                              %s - %s
                                        """.formatted(sup.getModel(), sup.getStatusSup())
                        ).build()).collect(Collectors.toList());
    }

}
