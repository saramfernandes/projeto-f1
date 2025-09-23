package senai.f1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.f1.dtos.request.CampeonatoRequestDTO;
import senai.f1.dtos.response.CampeonatoResponseDTO;
import senai.f1.service.CampeonatoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/campeonatos")
@RequiredArgsConstructor
public class CampeonatoController {
    private final CampeonatoService campeonatoService;

    @PostMapping
    public ResponseEntity<CampeonatoResponseDTO> create(@RequestBody @Valid CampeonatoRequestDTO dto) {
        return ResponseEntity.ok(campeonatoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDTO>> listAll() {
        return ResponseEntity.ok(campeonatoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(campeonatoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> update(@PathVariable UUID id,
                                                        @RequestBody @Valid CampeonatoRequestDTO dto) {
        return ResponseEntity.ok(campeonatoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        campeonatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consulta customizada
    @GetMapping("/buscar/equipe")
    public ResponseEntity<List<CampeonatoResponseDTO>> findByEquipe(@RequestParam String nome) {
        return ResponseEntity.ok(campeonatoService.findByEquipe(nome));
    }
}

