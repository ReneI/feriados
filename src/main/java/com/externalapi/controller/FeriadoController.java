package com.externalapi.controller;

import com.externalapi.service.FeriadoService;
import com.externalapi.service.FeriadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class FeriadoController {

    @Autowired
    private FeriadoService filmService;

    @GetMapping
    public com.externalapi.model.Feriado[] getAllFilms() {
        return filmService.findAllFeriados();
    }


}
