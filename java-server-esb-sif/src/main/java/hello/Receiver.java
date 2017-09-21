package hello;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

@Component
public class Receiver {

	public String receiveMessage(String message) throws ParserConfigurationException, SAXException, IOException {
//		System.out.println("Received <" + message + ">");
//		System.out.println("Response is <"+message+">");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(message);
		new java.
		db.parse(new java.io.StringBufferInputStream(message));
		return message;
	}
}