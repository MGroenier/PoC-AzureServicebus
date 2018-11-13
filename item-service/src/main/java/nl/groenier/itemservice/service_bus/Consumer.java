package nl.groenier.itemservice.service_bus;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import nl.groenier.itemservice.models.Item;
import nl.groenier.itemservice.repositories.ItemRepository;
import nl.groenier.trackingsystem.MessageBodyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Consumer {

//	@Autowired
	private SubscriptionClient subscriptionClient;

	private String connectionString = "Endpoint=sb://tracking-system.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=l3W7sB4oWl9ct6JPNfE1QUkBQCQSIclgZzw+ScxJWkQ=";



	public Consumer() throws Exception {
		subscriptionClient = new SubscriptionClient(new ConnectionStringBuilder(connectionString, "item-service-topic/subscriptions/test-topic-subscription"), ReceiveMode.PEEKLOCK);
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		registerMessageHandlerOnClient(subscriptionClient, executorService);
		subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());
	}

	private class MessageHandler implements IMessageHandler {

		@Autowired
		private ItemRepository itemRepository;

		public CompletableFuture<Void> onMessageAsync(IMessage message) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);

			if(message.getLabel() == "create user") {
				System.out.println("received message with CREATE USER label.");
				Item anObject = MessageBodyConverter.deserialize(messageString, Item.class);
				itemRepository.save(anObject);
			}
			// Deserialization
			Item anObject = MessageBodyConverter.deserialize(messageString, Item.class);

			System.out.println(dateFormat.format(date) + " --- Received message: " + anObject.toString());
			return CompletableFuture.completedFuture(null);
		}

		public void notifyException(Throwable exception, ExceptionPhase phase) {
			System.out.println(phase + " encountered exception:" + exception.getMessage());
		}
	}

//	void registerMessageHandlerOnClient(SubscriptionClient receiveClient, ExecutorService executorService) throws Exception {
//
//		// register the RegisterMessageHandler callback
//		receiveClient.registerMessageHandler(
//				new IMessageHandler() {
//					// callback invoked when the message handler loop has obtained a message
//					public CompletableFuture<Void> onMessageAsync(IMessage message) {
//						// receives message is passed to callback
//						if (message.getLabel() != null &&
//								message.getContentType() != null &&
//								message.getLabel().contentEquals("Scientist") &&
//								message.getContentType().contentEquals("application/json")) {
//
////							byte[] body = message.getBody();
////							Map scientist = GSON.fromJson(new String(body, UTF_8), Map.class);
////
////							System.out.printf(
////									"\n\t\t\t\t%s Message received: \n\t\t\t\t\t\tMessageId = %s, \n\t\t\t\t\t\tSequenceNumber = %s, \n\t\t\t\t\t\tEnqueuedTimeUtc = %s," +
////											"\n\t\t\t\t\t\tExpiresAtUtc = %s, \n\t\t\t\t\t\tContentType = \"%s\",  \n\t\t\t\t\t\tContent: [ firstName = %s, name = %s ]\n",
////									receiveClient.getEntityPath(),
////									message.getMessageId(),
////									message.getSequenceNumber(),
////									message.getEnqueuedTimeUtc(),
////									message.getExpiresAtUtc(),
////									message.getContentType(),
////									scientist != null ? scientist.get("firstName") : "",
////									scientist != null ? scientist.get("name") : "");
//							System.out.println("hoi");
//						}
//						return receiveClient.completeAsync(message.getLockToken());
//					}
//
//					// callback invoked when the message handler has an exception to report
//					public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
//						System.out.printf(exceptionPhase + "-" + throwable.getMessage());
//					}
//				},
//				// 1 concurrent call, messages are auto-completed, auto-renew duration
//				new MessageHandlerOptions(1, false, Duration.ofMinutes(1)),
//				executorService);
//
//	}


}
