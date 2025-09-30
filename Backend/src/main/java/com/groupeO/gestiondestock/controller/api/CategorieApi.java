package com.groupeO.gestiondestock.controller.api;

import com.groupeO.gestiondestock.dto.CategorieRequestDto;
import com.groupeO.gestiondestock.dto.CategorieResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.groupeO.gestiondestock.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/categories")
public interface CategorieApi {

    @Operation(summary = "Créer une nouvelle catégorie")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategorieResponseDto> save(@RequestBody CategorieRequestDto categorieDto);

    @GetMapping(value = "/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieResponseDto findById(@PathVariable("idCategorie") Integer id);

    @GetMapping(value = "/showAll", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategorieResponseDto> findAll();

    @DeleteMapping(value = "/delete/{idCategorie}")
    ResponseEntity<Void> delete(@PathVariable("idCategorie") Integer id);

    @Operation(summary = "Mettre à jour une catégorie existante")
    @PutMapping(value = "/update/{idCategorie}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategorieResponseDto> update(@PathVariable("idCategorie") Integer idCategorie, @RequestBody CategorieRequestDto categorieDto);

    @GetMapping(value = "/code/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieResponseDto findByCodeCategorie(@PathVariable("codeCategorie") String codeCategorie);
} 