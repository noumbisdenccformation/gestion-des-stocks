package com.groupeO.gestiondestock.controller.api;

import com.groupeO.gestiondestock.dto.CommandeFournisseurRequestDto;
import com.groupeO.gestiondestock.dto.CommandeFournisseurResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.groupeO.gestiondestock.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/commandesfournisseurs")
public interface CommandeFournisseurApi {

    @Operation(summary = "Créer une nouvelle commande fournisseur")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurResponseDto> save(@RequestBody CommandeFournisseurRequestDto commandeFournisseurDto);

    @GetMapping(value = "/{idCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurResponseDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = "/code/{codeCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurResponseDto findByCodeCommandeFournisseur(@PathVariable("codeCommandeFournisseur") String codeCommandeFournisseur);

    @GetMapping(value = "/showAll", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurResponseDto> findAll();

    @DeleteMapping(value = "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

    @Operation(summary = "Mettre à jour une commande fournisseur existante")
    @PutMapping(value = "/update/{idCommandeFournisseur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeFournisseurResponseDto> update(@PathVariable("idCommandeFournisseur") Integer idCommandeFournisseur, @RequestBody CommandeFournisseurRequestDto commandeFournisseurDto);
} 