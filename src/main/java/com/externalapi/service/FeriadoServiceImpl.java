package com.externalapi.service;

import com.externalapi.model.Feriado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.List;

@Service
public class FeriadoServiceImpl implements FeriadoService {

    @Autowired
    private RestTemplate template = new RestTemplate();

    Feriado[] feriados = template.getForObject("https://feriados-cl-api.herokuapp.com/feriados", Feriado[].class);


    @Override
    public Feriado[] findAllFeriados() {
        return feriados;
    }


    @Override
    public Map<Integer, String> findExtra() {
        Map<Integer, String> map = new HashMap<>();
        List<Feriado> feriadoList = Arrays.asList(feriados);
        int mapKey = 1;
        for (Feriado feriado : feriadoList) {
            String extra = feriado.getExtra();
            if (!map.containsValue(extra)) {
                map.put(mapKey, extra);
                mapKey++;
            }
        }
        return map;
    }

    @Override
    public List<Feriado> findByDate(List<Feriado> list) {
        list.sort(Comparator.comparing(f -> f.getDate()));
        Collections.reverse(list);
        return list;
    }

}
