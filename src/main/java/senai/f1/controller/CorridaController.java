package senai.f1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.f1.dtos.request.CorridaRequestDTO;
import senai.f1.dtos.response.CorridaResponseDTO;
import senai.f1.service.CorridaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/corridas")
@RequiredArgsConstructor
public class CorridaController {
    private final CorridaService corridaService;

    @PostMapping
    public ResponseEntity<CorridaResponseDTO> create(@RequestBody @Valid CorridaRequestDTO dto) {
        return ResponseEntity.ok(corridaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CorridaResponseDTO>> listAll() {
        return ResponseEntity.ok(corridaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorridaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(corridaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorridaResponseDTO> update(@PathVariable UUID id,
                                                     @RequestBody @Valid CorridaRequestDTO dto) {
        return ResponseEntity.ok(corridaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        corridaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consultas customizadas
    @GetMapping("/buscar/piloto")
    public ResponseEntity<List<CorridaResponseDTO>> findByPiloto(@RequestParam String nome) {
        return ResponseEntity.ok(corridaService.findByPiloto(nome));
    }

    @GetMapping("/buscar/pais")
    public ResponseEntity<List<CorridaResponseDTO>> findByPais(@RequestParam String nome) {
        return ResponseEntity.ok(corridaService.findByPais(nome));
    }
}

