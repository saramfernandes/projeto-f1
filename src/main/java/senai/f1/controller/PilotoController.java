package senai.f1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Criar piloto",
            description = "Cria um novo piloto no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Piloto criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PilotoResponseDTO> create(@RequestBody @Valid PilotoRequestDTO dto) {
        return ResponseEntity.ok(pilotoService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar pilotos",
            description = "Retorna a lista de todos os pilotos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            })
    public ResponseEntity<List<PilotoResponseDTO>> listAll() {
        return ResponseEntity.ok(pilotoService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar piloto por ID",
            description = "Retorna os dados de um piloto específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Piloto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Piloto não encontrado")
            })
    public ResponseEntity<PilotoResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pilotoService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar piloto",
            description = "Atualiza os dados de um piloto específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Piloto atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Piloto não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PilotoResponseDTO> update(@PathVariable UUID id,
                                                    @RequestBody @Valid PilotoRequestDTO dto) {
        return ResponseEntity.ok(pilotoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar piloto",
            description = "Remove um piloto do sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Piloto deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Piloto não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pilotoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/equipe")
    @Operation(summary = "Buscar pilotos por equipe",
            description = "Retorna todos os pilotos de uma equipe específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pilotos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum piloto encontrado para a equipe informada")
            })
    public ResponseEntity<List<PilotoResponseDTO>> findByEquipe(@RequestParam String nome) {
        return ResponseEntity.ok(pilotoService.findByEquipe(nome));
    }
}
