package com.example.ejercicio_clase9.controller;

import com.example.ejercicio_clase9.entity.Characters;
import com.example.ejercicio_clase9.repository.CharactersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ws/personaje")
public class WSController {
    final CharactersRepository charactersRepository;
    public WSController(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    //Listar personajes
    @GetMapping(value = "/list")
    public List<Characters> listCharacters() {
        return charactersRepository.findAll();
    }

    //Listar por ID
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<HashMap<String, Object>> getCharacterById(@PathVariable("id") String idStr) {

        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            Optional<Characters> optCharacters = charactersRepository.findById(Integer.parseInt(idStr));
            if (optCharacters.isPresent()) {
                responseJson.put("", optCharacters.get());
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("error", "ID Personaje NO encontrado");
                responseJson.put("date", LocalDateTime.now());
            }
        } catch (NumberFormatException e) {
            responseJson.put("error", "El id del personaje debe ser un número entero positivo");
        }
        return ResponseEntity.badRequest().body(responseJson);
    }

    //Actualizar o guardar personaje


    //Borrar personaje
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteCharacter(@PathVariable("id") String idStr) {
        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            int id = Integer.parseInt(idStr);
            if (charactersRepository.existsById(id)) {
                charactersRepository.deleteById(id);
                responseJson.put("estado", "borrado exitoso");
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("error", "ID Personaje no encontrado");
                responseJson.put("date", LocalDateTime.now());
                return ResponseEntity.badRequest().body(responseJson);
            }
        } catch (NumberFormatException e) {
            responseJson.put("estado", "error");
            responseJson.put("error", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(responseJson);
        }
    }

}
