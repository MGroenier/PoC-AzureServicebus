package nl.groenier.itemservice.service_bus;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.models.Item;
import nl.groenier.itemservice.models.Location;
import nl.groenier.itemservice.repositories.ItemRepository;
import nl.groenier.itemservice.repositories.LocationRepository;
import nl.groenier.trackingsystem.MessageBodyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Consumer {

//	@Autowired
	private SubscriptionClient subscriptionClient;

	private String connectionString = "Endpoint=sb://tracking-system.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=l3W7sB4oWl9ct6JPNfE1QUkBQCQSIclgZzw+ScxJWkQ=";
	private String enityPath = "item-service-topic/subscriptions/test-topic-subscription";

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private LocationRepository locationRepository;


	public Consumer() throws Exception {
		subscriptionClient = new SubscriptionClient(new ConnectionStringBuilder(connectionString, enityPath), ReceiveMode.PEEKLOCK);
		ExecutorService executorService = Executors.newCachedThreadPool();
		registerMessageHandlerOnClient(subscriptionClient, executorService);
	}

	void registerMessageHandlerOnClient(SubscriptionClient receiveClient, ExecutorService executorService) throws Exception {

		receiveClient.registerMessageHandler(new IMessageHandler() {
			@Override
			public CompletableFuture<Void> onMessageAsync(IMessage message) {

				String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
				Item receivedItem = MessageBodyConverter.deserialize(messageString, Item.class);

				switch (message.getLabel()) {
					case "item-create":
						itemRepository.save(receivedItem);
						break;
					case "item-update":
						itemRepository.save(receivedItem);
						break;
					case "item-delete":
						itemRepository.delete(receivedItem);
						break;
					case "item-read":
						//TODO How to implement this? How do we return the result to the client that performed the request.
						System.out.println("Read Item!");
						break;
				}

				return receiveClient.completeAsync(message.getLockToken());
			}

			@Override
			public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
				System.out.println("notify exception!");
			}
		});

	}


}
