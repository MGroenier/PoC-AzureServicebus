package nl.groenier.itemservice.service_bus;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.models.Item;
import nl.groenier.itemservice.repositories.ItemRepository;
import nl.groenier.trackingsystem.MessageBodyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Consumer {

//	@Autowired
//	private QueueClient queueClient;

	@Autowired
	private SubscriptionClient subscriptionClient;

//	public void receiveQueueMessage() throws ServiceBusException, InterruptedException {
//		queueClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());
//
//		TimeUnit.SECONDS.sleep(5);
//		queueClient.close();
//	}

	public void receiveTopicMessage() throws ServiceBusException, InterruptedException {
		subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());
	}

	static class MessageHandler implements IMessageHandler {

		@Autowired
		private ItemRepository itemRepository;

		public CompletableFuture<Void> onMessageAsync(IMessage message) {
			final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);

			if(message.getLabel() == "create user") {
				System.out.println("received message with CREATE USER label.");
				Item anObject = MessageBodyConverter.deserialize(messageString, Item.class);
				itemRepository.save(anObject);
			}
			// Deserialization
			Item anObject = MessageBodyConverter.deserialize(messageString, Item.class);

			System.out.println("Received message: " + anObject.toString());
			return CompletableFuture.completedFuture(null);
		}

		public void notifyException(Throwable exception, ExceptionPhase phase) {
			System.out.println(phase + " encountered exception:" + exception.getMessage());
		}
	}

}
