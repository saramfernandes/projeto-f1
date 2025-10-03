package senai.f1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.f1.dtos.request.PaisRequestDTO;
import senai.f1.dtos.response.PaisResponseDTO;
import senai.f1.service.PaisService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paises")
@RequiredArgsConstructor
public class PaisController {
    private final PaisService paisService;

    @PostMapping
    public ResponseEntity<PaisResponseDTO> create(@RequestBody @Valid PaisRequestDTO dto) {
        return ResponseEntity.ok(paisService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaisResponseDTO>> listAll() {
        return ResponseEntity.ok(paisService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(paisService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PaisRequestDTO dto) {
        return ResponseEntity.ok(paisService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Consulta customizada
    @GetMapping("/buscar")
    public ResponseEntity<PaisResponseDTO> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(paisService.findByNome(nome));
    }
}

