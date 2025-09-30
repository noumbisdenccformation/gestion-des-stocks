package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.ClientRequestDto;
import com.groupeO.gestiondestock.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {

    ClientResponseDto save(ClientRequestDto clientDto);

    ClientResponseDto findById(Integer id);

    ClientResponseDto findByNomClient(String nom);

    List<ClientResponseDto> findAll();

    void delete(Integer id);

    ClientResponseDto update(Integer id, ClientRequestDto clientDto);
}
