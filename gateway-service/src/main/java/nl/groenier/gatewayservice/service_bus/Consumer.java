package nl.groenier.gatewayservice.service_bus;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Consumer {

	@Autowired
	private QueueClient queueClient;
	@Autowired
	private SubscriptionClient subscriptionClient;

	public void receiveQueueMessage() throws ServiceBusException, InterruptedException {
		queueClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());
//		TimeUnit.SECONDS.sleep(5);
//		queueClient.close();
	}

	public void receiveTopicMessage() throws ServiceBusException, InterruptedException {
		subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());

//		TimeUnit.SECONDS.sleep(5);
//		subscriptionClient.close();
	}

	static class MessageHandler implements IMessageHandler {
		public CompletableFuture<Void> onMessageAsync(IMessage message) {
			final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
			System.out.println("Received message: " + messageString);
			return CompletableFuture.completedFuture(null);
		}

		public void notifyException(Throwable exception, ExceptionPhase phase) {
			System.out.println(phase + " encountered exception:" + exception.getMessage());
		}
	}

}