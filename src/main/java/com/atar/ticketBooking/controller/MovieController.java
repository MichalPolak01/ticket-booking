package com.atar.ticketBooking.controller;

import com.atar.ticketBooking.model.Movie;
import com.atar.ticketBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/movies")
//public class MovieController {
//
//    @Autowired
//    private MovieService movieService;
//
//    @GetMapping
//    public List<Movie> getAllMovies() {
//        return movieService.getAllMovies();
//    }
//}


//@Controller
//public class MovieController {
//
//    // Endpoint dla User Task do wyświetlenia formularza
//    @GetMapping("/select-movie-form")
//    public String showSelectMovieForm(Model model) {
//        // Przekaż listę filmów do widoku (pobierana z Camunda Variables)
//        List<Movie> movies = fetchMoviesFromCamunda(); // Implementuj logikę pobrania zmiennych procesu
//        model.addAttribute("movies", movies);
//        return "select-movie-form";
//    }
//
//    // Endpoint dla User Task do obsługi wybranego filmu
//    @PostMapping("/submit-selected-movie")
//    public String handleSelectedMovie(String selectedMovie) {
//        System.out.println("Selected movie ID: " + selectedMovie);
//
//        // Możesz zapisać wybrany film do zmiennej procesu lub wykonać inną logikę
//        updateCamundaProcessWithSelectedMovie(selectedMovie);
//
//        return "redirect:/next-task";
//    }
//
//    private List<Movie> fetchMoviesFromCamunda() {
//        // TODO: Pobierz zmienne z Camundy (np. za pomocą REST API)
//        return List.of(); // Przykładowa implementacja
//    }
//
//    private void updateCamundaProcessWithSelectedMovie(String selectedMovie) {
//        // TODO: Zapisz wybrany film jako zmienną procesu (np. za pomocą REST API)
//    }
//}

//@Controller
//public class MovieController {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    // Wyświetlenie formularza wyboru filmu
//    @GetMapping("/select-movie-form")
//    public String showSelectMovieForm(Model model) {
//        // Pobierz zmienne procesu (movies)
//        String processInstanceId = "Activity_0927zeu"; // Podaj ID instancji procesu
////        String url = "http://localhost:8080/engine-rest/process-instance/" + processInstanceId + "/variables/movies";
////        String url = "/engine-rest/process-instance/" + processInstanceId + "/variables/movies";
//        String url = "http://localhost:8080/engine-rest/process-instance/" + processInstanceId + "/variables/movies";
//
//
//        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//        List<Map<String, Object>> movies = (List<Map<String, Object>>) response.get("value");
//
//        // Przekaż zmienną do widoku
//        model.addAttribute("movies", movies);
//        return "select-movie-form"; // Widok JSP
//    }
//
//    // Obsługa wyboru filmu
//    @PostMapping("/submit-selected-movie")
//    public String handleSelectedMovie(String selectedMovie) {
//        System.out.println("Selected movie ID: " + selectedMovie);
//
//        // Zapisanie wybranego filmu w zmiennej procesu
//        String processInstanceId = "ID_PROCESU"; // Podaj ID instancji procesu
//        String url = "http://localhost:8080/engine-rest/process-instance/" + processInstanceId + "/variables";
//
//        Map<String, Object> variable = Map.of("selectedMovie", Map.of("value", selectedMovie));
//        restTemplate.postForObject(url, variable, Void.class);
//
//        // Przekierowanie do kolejnego kroku
//        return "redirect:/next-task";
//    }
//}

@Controller
public class MovieController {

    @GetMapping(value = "/run")
    public String runProcess() {
        return "startProcess";
    }

    private final RestTemplate restTemplate = new RestTemplate();

    // Wyświetlenie formularza wyboru filmu
    @GetMapping("/select-movie-form")
    public String showSelectMovieForm(Model model) {
        // Pobierz zmienne procesu (movies)
        String processInstanceId = "4503599627375311"; // Podaj ID instancji procesu
        String url = "http://localhost:8080/engine-rest/process-instance/" + processInstanceId + "/variables/movies";

        // Use RestTemplate to get the response
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> movies = (List<Map<String, Object>>) response.get("value");

        // Pass the movies to the view
        model.addAttribute("movies", movies);
        return "select-movie-form"; // Widok JSP
    }

    // Obsługa wyboru filmu
    @PostMapping("/submit-selected-movie")
    public String handleSelectedMovie(String selectedMovie) {
        System.out.println("Selected movie ID: " + selectedMovie);

        // Save the selected movie in the process variable
        String processInstanceId = "ID_PROCESU"; // Podaj ID instancji procesu
        String url = "http://localhost:8080/engine-rest/process-instance/" + processInstanceId + "/variables/selectedMovie";

        Map<String, Object> request = new HashMap<>();
        request.put("value", selectedMovie);
        request.put("type", "String");

        restTemplate.postForObject(url, request, Void.class);

        return "redirect:/success"; // Redirect after successful submission
    }
}
