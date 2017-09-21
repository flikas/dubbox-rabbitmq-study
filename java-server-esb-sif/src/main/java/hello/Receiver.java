package hello;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

	public String receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		System.out.println("Response is <"+message+">");
		return message;
	}
}