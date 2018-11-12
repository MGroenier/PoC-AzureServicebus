package nl.groenier.itemservice.repositories;

import nl.groenier.itemservice.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
