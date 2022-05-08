package com.externalapi.service;

import com.externalapi.model.Feriado;

import java.util.List;
import java.util.Map;

public interface FeriadoService {
    Feriado[] findAllFeriados();

    Map<Integer, String> findExtra();

    List<Feriado> findByDate(List<Feriado> list);
}
