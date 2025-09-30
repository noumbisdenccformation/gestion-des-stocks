package com.groupeO.gestiondestock.controller.api;

import com.groupeO.gestiondestock.dto.CommandeClientRequestDto;
import com.groupeO.gestiondestock.dto.CommandeClientResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.groupeO.gestiondestock.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/commandesclients")
public interface CommandeClientApi {

    @Operation(summary = "Créer une nouvelle commande client")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientResponseDto> save(@RequestBody CommandeClientRequestDto commandeClientDto);

    @GetMapping(value = "/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientResponseDto findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value = "/code/{codeCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientResponseDto findByCodeCommandeClient(@PathVariable("codeCommandeClient") String codeCommandeClient);

    @GetMapping(value = "/showAll", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientResponseDto> findAll();

    @DeleteMapping(value = "/delete/{idCommandeClient}")
    void delete(@PathVariable("idCommandeClient") Integer id);

    @Operation(summary = "Mettre à jour une commande client existante")
    @PutMapping(value = "/update/{idCommandeClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientResponseDto> update(@PathVariable("idCommandeClient") Integer idCommandeClient, @RequestBody CommandeClientRequestDto commandeClientDto);
} 