package nl.groenier.itemservice.repositories;

import nl.groenier.itemservice.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByTargetCity(String targetCity);
	List<Item> findAll();
	long count();

}
