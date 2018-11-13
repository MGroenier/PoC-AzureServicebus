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
	private TopicClient topicClient;

	public void sendTopicMessage(String body, String label) throws ServiceBusException, InterruptedException {
		Message message = new Message(body.getBytes(StandardCharsets.UTF_8));
		message.setLabel(label);
		topicClient.sendAsync(message);
	}

}
