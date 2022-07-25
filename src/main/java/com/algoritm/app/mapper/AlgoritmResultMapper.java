package com.algoritm.app.mapper;

import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.dto.AlgoritmSearchResultDto;
import com.algoritm.app.entity.dto.AlgoritmSortResultDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlgoritmResultMapper {
    AlgoritmSortResultDto toSortDto(AlgoritmResult algoritmResult);

    AlgoritmSearchResultDto toSearchDto(AlgoritmResult algoritmResult);

    List<AlgoritmSortResultDto> toSortDtoList(List<AlgoritmResult> algoritmResultList);

    List<AlgoritmSearchResultDto> toSearchDtoList(List<AlgoritmResult> algoritmResultList);
}
