package senai.f1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.f1.dtos.request.PistaRequestDTO;
import senai.f1.dtos.response.PistaResponseDTO;
import senai.f1.enums.Dificuldade;
import senai.f1.service.PistaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pistas")
@RequiredArgsConstructor
public class PistaController {
    private final PistaService pistaService;

    @PostMapping
    public ResponseEntity<PistaResponseDTO> create(@RequestBody @Valid PistaRequestDTO dto) {
        return ResponseEntity.ok(pistaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PistaResponseDTO>> listAll() {
        return ResponseEntity.ok(pistaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PistaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pistaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PistaResponseDTO> update(@PathVariable UUID id,
                                                   @RequestBody @Valid PistaRequestDTO dto) {
        return ResponseEntity.ok(pistaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consultas customizadas
    @GetMapping("/buscar/pais")
    public ResponseEntity<List<PistaResponseDTO>> findByPais(@RequestParam String nome) {
        return ResponseEntity.ok(pistaService.findByPais(nome));
    }

    @GetMapping("/buscar/dificuldade")
    public ResponseEntity<List<PistaResponseDTO>> findByDificuldade(@RequestParam Dificuldade dificuldade) {
        return ResponseEntity.ok(pistaService.findByDificuldade(dificuldade));
    }
}

