package nl.groenier.itemservice.controllers;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
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

}
