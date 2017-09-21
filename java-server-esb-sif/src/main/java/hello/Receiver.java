package hello;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

@Component
public class Receiver {

	public String receiveMessage(String message) throws ParserConfigurationException, SAXException, IOException,
			TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		// System.out.println("Received <" + message + ">");
		// System.out.println("Response is <"+message+">");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new java.io.ByteArrayInputStream(message.getBytes()));
//		doc.getElementById("str").setNodeValue("a");
		doc.appendChild(doc.createTextNode("abc"));
		javax.xml.transform.dom.DOMSource ds = new javax.xml.transform.dom.DOMSource(doc);
		java.io.StringWriter sw = new StringWriter();
		StreamResult rst = new StreamResult(sw);
		javax.xml.transform.TransformerFactory.newInstance().newTransformer(ds).transform(ds, rst);

		return sw.toString();
	}
}