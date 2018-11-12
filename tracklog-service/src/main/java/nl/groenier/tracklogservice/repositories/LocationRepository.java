package nl.groenier.tracklogservice.repositories;

import nl.groenier.tracklogservice.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {

}
