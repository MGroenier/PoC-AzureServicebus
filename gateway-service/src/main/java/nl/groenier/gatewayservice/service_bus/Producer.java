package nl.groenier.gatewayservice.service_bus;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Producer {

	@Autowired
	private QueueClient queueClient;
	@Autowired
	private TopicClient topicClient;

	// NOTE: Please be noted that below are the minimum code for demonstrating the usage of autowired clients.
	// For complete documentation of Service Bus, reference https://azure.microsoft.com/en-us/services/service-bus/
	public void sendQueueMessage(String messageBody) throws ServiceBusException, InterruptedException {
		System.out.println("Sending message: " + messageBody);
		Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
		message.setContentType("application/json");
		queueClient.send(message);
	}

	public void sendTopicMessage(String messageBody) throws ServiceBusException, InterruptedException {
		System.out.println("Sending message to topic: " + messageBody);
		final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
		topicClient.send(message);
//		topicClient.close();
	}

}
