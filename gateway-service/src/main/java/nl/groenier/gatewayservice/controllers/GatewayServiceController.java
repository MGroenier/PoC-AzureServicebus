package nl.groenier.gatewayservice.controllers;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.gatewayservice.models.Item;
import nl.groenier.gatewayservice.models.Location;
import nl.groenier.gatewayservice.service_bus.Consumer;
import nl.groenier.gatewayservice.service_bus.Producer;
import nl.groenier.trackingsystem.MessageBodyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tracking-system")
public class GatewayServiceController {

	@Autowired
	Producer producer;

//	@Autowired
//	Consumer consumer;

	@GetMapping(value = "")
	public String index() {
		return "Tracking-System Gateway Service, up and running!";
	}

	@GetMapping(value = "/items")
	public String getAllItems() {
		return "Return all items.";
	}

	@GetMapping(value = "/items/1")
	public Item getItemById() {
		return new Item("Some simple item.", 5.5, 1, 2);
	}

	@PostMapping(value = "/items")
	public void createItem(@RequestBody Item item) throws ServiceBusException, InterruptedException {
		producer.sendTopicMessage(MessageBodyConverter.serialize(item));
	}

}
