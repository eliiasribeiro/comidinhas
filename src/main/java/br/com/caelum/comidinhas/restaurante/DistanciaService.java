package br.com.caelum.comidinhas.restaurante;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class DistanciaService {

    private final Random random = new Random();

    Integer calculaDistancia() {
        return random.nextInt(100);
    }

}
