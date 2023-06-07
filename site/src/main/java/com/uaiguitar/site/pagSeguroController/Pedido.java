package com.uaiguitar.site.pagSeguroController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import com.uaiguitar.site.controller.UsuarioController;
import com.uaiguitar.site.entidades.Curso;
import com.uaiguitar.site.entidades.Usuario;

import com.uaiguitar.site.pagSeguro.Items;
import com.uaiguitar.site.service.CursoService;
import com.uaiguitar.site.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Pedido {
    private static final String TOKEN = "B41DCA8BF65F430A9215BB083FEE45B0";

    final
    UsuarioController controller;

    final
    UsuarioService service;

    final
    CursoService cursoService;

    public Pedido(UsuarioController controller, UsuarioService service, CursoService cursoService) {
        this.controller = controller;
        this.service = service;
        this.cursoService = cursoService;
    }

    @PostMapping("/pedido")
    public void criarPedido(Curso c)
                                    throws IOException, 
                                    InterruptedException{
       System.out.println(c.getNome());
       Usuario usuario = service.findbyid(controller.logado().getId());
        Curso curso = cursoService.findByIdCurso(c.getId());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://sandbox.api.pagseguro.com/orders"))
            .header("Authorization", TOKEN)
            .header("content-type", "application/json")
            .method("POST", HttpRequest.BodyPublishers
            .ofString("{\"reference_id: " + curso.getId() +
                         ",\"customer\":{\"name: "+ usuario.getNomeCompleto() +
                          ",\"email: " + usuario.getEmail() +
//                          ",\"tax_id: " +usuario.getTax_id()+
//                          ",\"phone\":[{\"country: " + phone.getCountry()+
//                          ",\"area: " + phone.getArea() +
//                          ",\"number: " +phone.getNumber()+
//                          ",\"type:"+phone.getType()+
                          "}]},\"items\":[{\"name: "+curso.getNome()+
                          ",\"quantity:"+ 1 +
                          ",\"unit_amount: "+100+
//                          "}],\"shipping\":{\"address\":{\"street: "+address.getStreet()+
//                          ",\"number: "+address.getNumber()+
//                          ",\"complement: "+address.getComplement()+
//                          ",\"locality: "+ address.getLocality()+
//                          ",\"city: "+address.getCity()+
//                          ",\"region_code:"+address.getRegion_code()+
//                          ",\"country: "+address.getCountry()+
//                          ",\"postal_code: "+address.getPostal_code()+"}}"))
                            "}}"))
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

//        return ResponseEntity.ok().body(response.body());
    }
}
