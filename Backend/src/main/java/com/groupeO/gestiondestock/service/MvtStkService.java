package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.MvtStkRequestDto;
import com.groupeO.gestiondestock.dto.MvtStkResponseDto;

import java.util.List;

public interface MvtStkService {
    MvtStkResponseDto save(MvtStkRequestDto mvtStkDto);
    MvtStkResponseDto findById(Integer id);
    List<MvtStkResponseDto> findAll();
    void delete(Integer id);
    MvtStkResponseDto update(Integer id, MvtStkRequestDto mvtStkDto);
}
