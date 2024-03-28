package com.frank.mytest.converter;

import com.frank.mytest.dto.SchoolDTO;
import com.frank.mytest.entity.School;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolConverter {
    private final ModelMapper mapper;

    public SchoolDTO toDTO(School school) {
        SchoolDTO dto = mapper.map(school, SchoolDTO.class);
        return dto;
    }

    public School toEntity(SchoolDTO dto) {
        return mapper.map(dto, School.class);
    }
}
