package nl.groenier.itemservice.controllers;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.models.Item;
import nl.groenier.itemservice.repositories.ItemRepository;
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

	@PostMapping(value = "/produce-queue")
	public void sendMessageToQueue(@RequestParam("message") String message) throws ServiceBusException, InterruptedException {
		producer.sendQueueMessage(message);
	}

	@GetMapping(value = "/consume-queue")
	public void consumeMessageFromQueue() throws ServiceBusException, InterruptedException {
		consumer.receiveQueueMessage();
	}

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

		//create some new records
		itemRepository.save(new Item("Amsterdam"));
		itemRepository.save(new Item("Amsterdam"));
		itemRepository.save(new Item("Amsterdam"));
		itemRepository.save(new Item("Amsterdam"));
		itemRepository.save(new Item("Vlissingen"));
		itemRepository.save(new Item("Vlissingen"));


	}

	@GetMapping(value = "get-all")
	public void getAllItems() {
		for (Item item:itemRepository.findAll()) {
			System.out.println(item);
		}
	}

	@GetMapping(value = "get-by-target-city")
	public void getByTargetCity() {
		for (Item item:itemRepository.findByTargetCity("Amsterdam")) {
			System.out.println(item);
		}
	}

	@GetMapping(value = "get-count")
	public void getCount() {
		System.out.println(itemRepository.count());
	}

}
