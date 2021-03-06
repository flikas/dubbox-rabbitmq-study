package hello;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static String queueName = "rpc_queue";
	private static String host = "192.168.56.10";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	// @Bean
	// DirectExchange exchange() {
	// return new DirectExchange("amq.direct");
	//// return new TopicExchange("amq.direct");
	// }
	//
	// @Bean
	// Binding binding(Queue queue, TopicExchange exchange) {
	// return BindingBuilder.bind(queue).to(exchange).with(queueName);
	// }

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cf = new CachingConnectionFactory(host);
		cf.setUsername("flikas");
		cf.setPassword("pengfei");
		cf.createConnection();
		return cf;
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) throws InterruptedException {
		if (args.length >= 1)
			host = args[0];
		if (args.length >= 2)
			queueName = args[1];
		System.out.println("Receving from " + host + " queue " + queueName);
		SpringApplication.run(Application.class, args);
	}
}
