package ek.osnb.starter.controller;

import ek.osnb.starter.model.Movie;
import ek.osnb.starter.model.MovieDetails;
import ek.osnb.starter.repository.MovieRepository;
import ek.osnb.starter.service.ActorService;
import ek.osnb.starter.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(
            @PathVariable Long movieId,
            @PathVariable Long actorId) {
        Movie movie = movieService.addActorToMovie(movieId, actorId);

        return ResponseEntity.ok(movie);
    }

    @PostMapping("/{id}/details")
    public ResponseEntity<Movie> addDetailsToMovie(
            @PathVariable Long id,
            @RequestBody MovieDetails details) {
        Movie movie = movieService.addDetailsToMovie(id, details);
        movieRepository.save(movie); // er dette korrekt?
        return ResponseEntity.ok(movie);
    }
}
