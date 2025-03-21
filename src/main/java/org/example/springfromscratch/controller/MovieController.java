package org.example.springfromscratch.controller;

import org.example.springfromscratch.model.dto.MovieDTO;
import org.example.springfromscratch.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MovieController {
    final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String index(Model model) throws Exception {
        List<MovieDTO> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "index";
    }
}
