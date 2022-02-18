package com.cefet.salao.api.controllers;

import com.cefet.salao.api.entities.Conta;
import com.cefet.salao.api.entities.Tipo;
import com.cefet.salao.api.entities.Usuario;
import com.cefet.salao.api.services.ContaService;
import com.cefet.salao.api.services.TipoService;
import com.cefet.salao.api.services.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas")
@Api(value = "contas", tags = "Contas")
@CrossOrigin(origins = "*")
public class ContaController {

	@Autowired
	private ContaService contaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoService tipoService;

	public ContaController() {}

    @PostMapping("/")
    public ResponseEntity<Conta> createConta(@Valid @RequestBody Map<String, String> json) throws URISyntaxException, ParseException {
        if (json.get("id") != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma nova conta não pode ter um ID");
        }

        Conta novaConta = new Conta();
        novaConta.setFormapgt(json.get("formapgt"));
        novaConta.setHorario(json.get("horario"));
        novaConta.setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.get("dataAgendamento")));

        Optional<Usuario> usuario = usuarioService.findOne(Long.valueOf(json.get("usuario_id")));
        Optional<Tipo> tipo = tipoService.findOne(Long.valueOf(json.get("tipo_id")));

        if(usuario.isEmpty() || tipo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ou tipo não encontrados");
        }

        novaConta.setUsuario(usuario.get());
        novaConta.setTipo(tipo.get());

        Conta result = contaService.save(novaConta);
        return ResponseEntity.created(new URI("/api/contas/" + result.getId()))
                .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Conta> updateContas(@Valid @RequestBody  Map<String, String> json) throws URISyntaxException, ParseException {
        if (json.get("id") == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid conta id null");
        }


        Conta novaConta = new Conta();
        novaConta.setId(Long.valueOf(json.get("id")));
        novaConta.setFormapgt(json.get("formapgt"));
        novaConta.setHorario(json.get("horario"));
        novaConta.setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.get("dataAgendamento")));

        Optional<Usuario> usuario = usuarioService.findOne(Long.valueOf(json.get("usuario_id")));
        Optional<Tipo> tipo = tipoService.findOne(Long.valueOf(json.get("tipo_id")));

        if(usuario.isEmpty() || tipo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ou tipo não encontrados");
        }

        novaConta.setUsuario(usuario.get());
        novaConta.setTipo(tipo.get());

        Conta result = contaService.save(novaConta);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Long id) {
        Optional<Conta> conta = contaService.findOne(id);
        if(conta.isPresent()) {
            return ResponseEntity.ok().body(conta.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Conta>> getConta(){
       List<Conta> lista = contaService.findAllList();
       
       return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
