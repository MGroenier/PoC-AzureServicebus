package nl.groenier.tracklogservice.repositories;

import nl.groenier.tracklogservice.models.Tracklog;
import org.springframework.data.repository.CrudRepository;

public interface TracklogRepository extends CrudRepository<Tracklog, Long> {

}
