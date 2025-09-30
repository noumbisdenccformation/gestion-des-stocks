package com.groupeO.gestiondestock.controller.api;

import com.groupeO.gestiondestock.dto.MvtStkRequestDto;
import com.groupeO.gestiondestock.dto.MvtStkResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.groupeO.gestiondestock.utils.Constants.APP_ROOT;

@RequestMapping(APP_ROOT + "/mvtstk")
public interface MvtStkApi {

    @Operation(summary = "Créer un mouvement de stock")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkResponseDto> save(@RequestBody MvtStkRequestDto mvtStkDto);

    @GetMapping(value = "/{idMvtStk}", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkResponseDto findById(@PathVariable("idMvtStk") Integer id);

    @GetMapping(value = "/showAll", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStkResponseDto> findAll();

    @DeleteMapping(value = "/delete/{idMvtStk}")
    void delete(@PathVariable("idMvtStk") Integer id);

    @Operation(summary = "Mettre à jour un mouvement de stock")
    @PutMapping(value = "/update/{idMvtStk}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkResponseDto> update(@PathVariable("idMvtStk") Integer idMvtStk, @RequestBody MvtStkRequestDto mvtStkDto);
}

 