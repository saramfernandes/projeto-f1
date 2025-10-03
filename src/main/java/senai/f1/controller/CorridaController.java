package senai.f1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Criar corrida",
            description = "Cria uma nova corrida no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Corrida criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<CorridaResponseDTO> create(@RequestBody @Valid CorridaRequestDTO dto) {
        return ResponseEntity.ok(corridaService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar corridas",
            description = "Retorna a lista de todas as corridas cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            })
    public ResponseEntity<List<CorridaResponseDTO>> listAll() {
        return ResponseEntity.ok(corridaService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar corrida por ID",
            description = "Retorna os dados de uma corrida específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Corrida encontrada"),
                    @ApiResponse(responseCode = "404", description = "Corrida não encontrada")
            })
    public ResponseEntity<CorridaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(corridaService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar corrida",
            description = "Atualiza os dados de uma corrida específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Corrida atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Corrida não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<CorridaResponseDTO> update(@PathVariable UUID id,
                                                     @RequestBody @Valid CorridaRequestDTO dto) {
        return ResponseEntity.ok(corridaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar corrida",
            description = "Remove uma corrida do sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Corrida deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Corrida não encontrada")
            })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        corridaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Consultas customizadas
    @GetMapping("/buscar/piloto")
    @Operation(summary = "Buscar corridas por piloto",
            description = "Retorna todas as corridas em que um piloto específico participou",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Corridas encontradas"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma corrida encontrada para o piloto informado")
            })
    public ResponseEntity<List<CorridaResponseDTO>> findByPiloto(@RequestParam String nome) {
        return ResponseEntity.ok(corridaService.findByPiloto(nome));
    }

    @GetMapping("/buscar/pais")
    @Operation(summary = "Buscar corridas por país",
            description = "Retorna todas as corridas que ocorreram em um país específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Corridas encontradas"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma corrida encontrada para o país informado")
            })
    public ResponseEntity<List<CorridaResponseDTO>> findByPais(@RequestParam String nome) {
        return ResponseEntity.ok(corridaService.findByPais(nome));
    }
}
