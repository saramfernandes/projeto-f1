package senai.f1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Criar campeonato",
            description = "Cria um novo campeonato no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campeonato criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<CampeonatoResponseDTO> create(@RequestBody @Valid CampeonatoRequestDTO dto) {
        return ResponseEntity.ok(campeonatoService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar campeonatos",
            description = "Retorna a lista de todos os campeonatos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            })
    public ResponseEntity<List<CampeonatoResponseDTO>> listAll() {
        return ResponseEntity.ok(campeonatoService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar campeonato por ID",
            description = "Retorna os dados de um campeonato específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campeonato encontrado"),
                    @ApiResponse(responseCode = "404", description = "Campeonato não encontrado")
            })
    public ResponseEntity<CampeonatoResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(campeonatoService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar campeonato",
            description = "Atualiza os dados de um campeonato específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campeonato atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Campeonato não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<CampeonatoResponseDTO> update(@PathVariable UUID id,
                                                        @RequestBody @Valid CampeonatoRequestDTO dto) {
        return ResponseEntity.ok(campeonatoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar campeonato",
            description = "Remove um campeonato do sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Campeonato deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Campeonato não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        campeonatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consulta customizada
    @GetMapping("/buscar/equipe")
    @Operation(summary = "Buscar campeonatos por equipe",
            description = "Retorna todos os campeonatos em que uma equipe específica participa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campeonatos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum campeonato encontrado para a equipe informada")
            })
    public ResponseEntity<List<CampeonatoResponseDTO>> findByEquipe(@RequestParam String nome) {
        return ResponseEntity.ok(campeonatoService.findByEquipe(nome));
    }
}
