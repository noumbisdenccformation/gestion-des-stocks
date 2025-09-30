package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.CommandeClientRequestDto;
import com.groupeO.gestiondestock.dto.CommandeClientResponseDto;
import com.groupeO.gestiondestock.dto.LigneCommandeClientRequestDto;
import com.groupeO.gestiondestock.dto.LigneCommandeClientResponseDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientResponseDto save(CommandeClientRequestDto commandeClientDto);

    CommandeClientResponseDto findById(Integer id);

    CommandeClientResponseDto findByCodeCommandeClient(String code);

    List<CommandeClientResponseDto> findAll();

    void delete(Integer id);

    CommandeClientResponseDto update(Integer id, CommandeClientRequestDto commandeClientDto);

    CommandeClientResponseDto addLigne(Integer commandeId, LigneCommandeClientRequestDto ligneDto);

    CommandeClientResponseDto updateLigne(Integer commandeId, LigneCommandeClientRequestDto ligneDto);

    CommandeClientResponseDto removeLigne(Integer commandeId, Integer ligneId);
    List<LigneCommandeClientResponseDto> findAllLignesByCommandeId(Integer commandeId);
    CommandeClientResponseDto removeAllLignes(Integer commandeId);

}

