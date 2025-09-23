package senai.f1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.f1.dtos.request.PilotoRequestDTO;
import senai.f1.dtos.response.PilotoResponseDTO;
import senai.f1.service.PilotoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pilotos")
@RequiredArgsConstructor
public class PilotoController {
    private final PilotoService pilotoService;

    @PostMapping
    public ResponseEntity<PilotoResponseDTO> create(@RequestBody @Valid PilotoRequestDTO dto) {
        return ResponseEntity.ok(pilotoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PilotoResponseDTO>> listAll() {
        return ResponseEntity.ok(pilotoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pilotoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PilotoResponseDTO> update(@PathVariable UUID id,
                                                    @RequestBody @Valid PilotoRequestDTO dto) {
        return ResponseEntity.ok(pilotoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pilotoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consulta customizada
    @GetMapping("/buscar/equipe")
    public ResponseEntity<List<PilotoResponseDTO>> findByEquipe(@RequestParam String nome) {
        return ResponseEntity.ok(pilotoService.findByEquipe(nome));
    }
}

