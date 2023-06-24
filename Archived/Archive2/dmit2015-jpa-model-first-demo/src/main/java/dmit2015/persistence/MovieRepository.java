package dmit2015.persistence;

import dmit2015.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SecurityContext _securityContext;

    @Transactional
    public void add(Movie newMovie) {
        em.persist(newMovie);
    }

    public Optional<Movie> findById(Long movieId) {
        Optional<Movie> optionalMovie = Optional.empty();
        try {
            Movie querySingleResult = em.find(Movie.class, movieId);
            if (querySingleResult != null) {
                optionalMovie = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalMovie;
    }

    public List<Movie> findAll() {
        return em.createQuery("SELECT o FROM Movie o ", Movie.class)
                .getResultList();
    }

    @Transactional
    public Movie update(Long id, Movie updatedMovie) {
        Movie existingMovie = null;

        Optional<Movie> optionalMovie = findById(id);
        if (optionalMovie.isPresent()) {
            // Update only properties that is editable by the end user
            existingMovie = optionalMovie.orElseThrow();
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
            existingMovie.setRating(updatedMovie.getRating());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setPrice(updatedMovie.getPrice());

            existingMovie = em.merge(existingMovie);
        }

        return existingMovie;
    }

    @Transactional
    public void delete(Movie existingMovie) {
        if (em.contains(existingMovie)) {
            em.remove(existingMovie);
        } else {
            em.remove(em.merge(existingMovie));
        }
    }

    @Transactional
    public void deleteById(Long movieId) {
        Optional<Movie> optionalMovie = findById(movieId);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.orElseThrow();
            em.remove(existingMovie);
        }
    }

    public long count() {
        return em.createQuery("SELECT COUNT(o) FROM Movie o", Long.class).getSingleResult().longValue();
    }

    @Transactional
    public void deleteAll() {
        em.flush();
        em.clear();
        em.createQuery("DELETE FROM Movie").executeUpdate();
    }

}