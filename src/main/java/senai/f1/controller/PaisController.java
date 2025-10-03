package senai.f1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Criar país",
            description = "Cria um novo país no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "País criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PaisResponseDTO> create(@RequestBody @Valid PaisRequestDTO dto) {
        return ResponseEntity.ok(paisService.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar países",
            description = "Retorna a lista de todos os países cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            })
    public ResponseEntity<List<PaisResponseDTO>> listAll() {
        return ResponseEntity.ok(paisService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar país por ID",
            description = "Retorna os dados de um país específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "País encontrado"),
                    @ApiResponse(responseCode = "404", description = "País não encontrado")
            })
    public ResponseEntity<PaisResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(paisService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar país",
            description = "Atualiza os dados de um país específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "País atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "País não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public ResponseEntity<PaisResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PaisRequestDTO dto) {
        return ResponseEntity.ok(paisService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar país",
            description = "Remove um país do sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "País deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "País não encontrado")
            })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar país por nome",
            description = "Retorna os dados de um país a partir do nome",
            responses = {
                    @ApiResponse(responseCode = "200", description = "País encontrado"),
                    @ApiResponse(responseCode = "404", description = "País não encontrado")
            })
    public ResponseEntity<PaisResponseDTO> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(paisService.findByNome(nome));
    }
}
