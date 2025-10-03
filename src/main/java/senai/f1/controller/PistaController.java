package senai.f1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Criar pista",
            description = "Cria uma nova pista no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pista criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PistaResponseDTO> create(@RequestBody @Valid PistaRequestDTO dto) {
        return ResponseEntity.ok(pistaService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar pistas",
            description = "Retorna a lista de todas as pistas cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            })
    public ResponseEntity<List<PistaResponseDTO>> listAll() {
        return ResponseEntity.ok(pistaService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pista por ID",
            description = "Retorna os dados de uma pista específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pista encontrada"),
                    @ApiResponse(responseCode = "404", description = "Pista não encontrada")
            })
    public ResponseEntity<PistaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pistaService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pista",
            description = "Atualiza os dados de uma pista específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pista atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pista não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PistaResponseDTO> update(@PathVariable UUID id,
                                                   @RequestBody @Valid PistaRequestDTO dto) {
        return ResponseEntity.ok(pistaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pista",
            description = "Remove uma pista do sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pista deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pista não encontrada")
            })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/pais")
    @Operation(summary = "Buscar pistas por país",
            description = "Retorna todas as pistas localizadas em um país específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pistas encontradas"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma pista encontrada para o país informado")
            })
    public ResponseEntity<List<PistaResponseDTO>> findByPais(@RequestParam String nome) {
        return ResponseEntity.ok(pistaService.findByPais(nome));
    }

    @GetMapping("/buscar/dificuldade")
    @Operation(summary = "Buscar pistas por dificuldade",
            description = "Retorna todas as pistas com a dificuldade especificada",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pistas encontradas"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma pista encontrada com a dificuldade informada")
            })
    public ResponseEntity<List<PistaResponseDTO>> findByDificuldade(@RequestParam Dificuldade dificuldade) {
        return ResponseEntity.ok(pistaService.findByDificuldade(dificuldade));
    }
}
