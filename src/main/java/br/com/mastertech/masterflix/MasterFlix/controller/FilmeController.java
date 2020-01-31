package br.com.mastertech.masterflix.MasterFlix.controller;

import br.com.mastertech.masterflix.MasterFlix.model.Filme;
import br.com.mastertech.masterflix.MasterFlix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/")
    public String voltarIndex(){
        return "index";
    }

    @GetMapping("/cadastro")
    public String telaCadastro(){
        return "cadastro";
    }

    @GetMapping("/busca")
    public String buscaNome(){
        return "busca";
    }

    @GetMapping("/filmes")
    public ModelAndView listaFilmes(Filme filme, Model model){
        ModelAndView pagina = new ModelAndView("lista");
        Iterable<Filme> filmes = service.listarTodos();
        pagina.addObject("filmes", filmes);
        return pagina;
    }


    @GetMapping("/buscarNome")
    public String buscarNome(@RequestParam("nome") String nome, Model model){
        Filme filme = service.findByNome(nome);

        if (filme != null){
            model.addAttribute("filme", filme);
            return "listaFilmeNome";
        }
        else{
            model.addAttribute("msg", "O filme " + nome + "não foi encontrado! Tente novamente.");
            return "busca";
        }

    }

    @GetMapping("/buscarNome/{nome}")
    public String buscarNomeUrl(@PathVariable("nome") String nome, Model model){
        Filme filme = service.findByNome(nome);

        if (filme != null){
            model.addAttribute("filme", filme);
            return "listaFilmeNome";
        }
        else{
            model.addAttribute("msg", "O filme " + nome + "não foi encontrado! Tente novamente.");
            return "busca";
        }

    }

    @PostMapping("/cadastrar")
    public String cadastrarFilme(Filme filme){
        service.cadastrarFilme(filme);
        return "cadastro";
    }

}
