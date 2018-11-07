package nl.groenier.itemservice.repositories;

import nl.groenier.itemservice.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

	public List<Item> findByTargetCity(String targetCity);
	public List<Item> findAll();
	public long count();

}
