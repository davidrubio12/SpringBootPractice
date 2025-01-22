package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public Collection<Rating> findAllRatings(Long id) {
        return ratingRepository.findByProductId(id);
    }

    @Override
    public Optional<Double> findRatingById(Long id) {
        return ratingRepository.findRatingById(id);
    }


}
