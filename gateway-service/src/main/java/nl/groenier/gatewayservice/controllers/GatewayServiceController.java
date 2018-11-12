package nl.groenier.gatewayservice.controllers;

import nl.groenier.gatewayservice.models.Item;
import nl.groenier.gatewayservice.models.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tracking-system")
public class GatewayServiceController {

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
		Location incentroAmsterdam = new Location("Amsterdam", "Moermanskkade", 113, "", "1013CN", "NL", 5.0, 6.0);
		Location incentroUtrecht = new Location("Utrecht", "Eenstraat", 87, "", "1238KO", "NL", 8.0, 3.0);
		Item firstItem = new Item("Some simple item.", 5.5, incentroAmsterdam, incentroUtrecht);
		return firstItem;
	}



}
