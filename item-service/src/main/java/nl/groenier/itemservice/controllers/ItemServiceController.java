package nl.groenier.itemservice.controllers;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.models.Item;
import nl.groenier.itemservice.models.Location;
import nl.groenier.itemservice.repositories.ItemRepository;
import nl.groenier.itemservice.repositories.LocationRepository;
import nl.groenier.itemservice.service_bus.Consumer;
import nl.groenier.itemservice.service_bus.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item-service")
public class ItemServiceController {

	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private LocationRepository locationRepository;

	@PostMapping(value = "/produce-queue")
	public void sendMessageToQueue(@RequestParam("message") String message) throws ServiceBusException, InterruptedException {
//		Item item = new Item();
//		producer.sendQueueMessage(item);
	}

//	@GetMapping(value = "/consume-queue")
//	public void consumeMessageFromQueue() throws ServiceBusException, InterruptedException {
//		consumer.receiveQueueMessage();
//	}

	@PostMapping(value = "/produce-topic")
	public void sendMessageToTopic(@RequestParam("message") String message) throws ServiceBusException, InterruptedException {
		producer.sendTopicMessage(message);
	}

	@GetMapping(value = "/consume-topic")
	public void consumeMessageFromTopic() throws ServiceBusException, InterruptedException {
		consumer.receiveTopicMessage();
	}

	@GetMapping(value = "/reset-repository")
	public void resetRepository() {
		//delete all records of the database
		itemRepository.deleteAll();

		//create some locations
		//String city, String street, Integer streetnumber, String suffix, String postal_code, String country_code, double longitude, double latitude
		Location incentroAmsterdam = new Location("Amsterdam", "Moermanskkade", 113, "", "1013CN", "NL", 5.0, 6.0);
		Location incentroUtrecht = new Location("Utrecht", "Eenstraat", 87, "", "1238KO", "NL", 8.0, 3.0);
		locationRepository.save(incentroAmsterdam);
		locationRepository.save(incentroUtrecht);

		//create some items
		Item firstItem = new Item("Some simple item.", 5.5, incentroAmsterdam, incentroUtrecht);
		itemRepository.save(firstItem);

	}

	@GetMapping(value = "get-all")
	public void getAllItems() {
		for (Item item:itemRepository.findAll()) {
			System.out.println(item);
		}
	}

//	@GetMapping(value = "get-by-target-city")
//	public void getByTargetCity() {
//		for (Item item:itemRepository.findByTargetCity("Amsterdam")) {
//			System.out.println(item);
//		}
//	}

	@GetMapping(value = "get-count")
	public void getCount() {
		System.out.println(itemRepository.count());
	}

}
