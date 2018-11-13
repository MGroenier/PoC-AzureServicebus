package nl.groenier.itemservice;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.service_bus.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) throws Exception {
		Consumer consumer1 = new Consumer();
		SpringApplication.run(ItemServiceApplication.class, args);
	}
}
