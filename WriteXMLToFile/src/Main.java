import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.w3c.dom.Document;

public class Main {
	private static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>" + "<company>"
			+ "<staff id=\"1\">" + "<firstname>yong</firstname>" + "<lastname>mook kim</lastname>"
			+ "<nickname>mkyong</nickname>" + "<salary>100000</salary>" + "</staff>" + "</company>";

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String starttime = sdf.format(new Date());
		writeXMLToFile(xml, "source.xml");
		String key = "Mary has one cat";
		File inputFile = new File("source.xml");
		File encryptedFile = new File("encrypted.xml");
		
		System.out.println("Done");
		try {
			System.out.println("Before encrypt: " + sdf.format(new Date()));
			CryptoUtils.encrypt(key, inputFile, encryptedFile);
			deleteFile("source.xml");
			System.out.println("After encrypt: " + sdf.format(new Date()));
			//CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
		} catch (CryptoException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Before decrypt: " + sdf.format(new Date()));
		decrypt();
		System.out.println("After decrypt: " + sdf.format(new Date()));
		System.out.println("Starttime: " + starttime + ". Endtime: " + sdf.format(new Date()));
	}
	public static void decrypt() throws CryptoException{
		File inputFile = new File("encrypted.xml");
		File decryptedFile = new File("decrypted.xml");
		CryptoUtils.decrypt("Mary has one cat", inputFile, decryptedFile);
		//deleteFile("encrypted.xml");
	}
	
	public static String encryptFile(String key, String fileURL, String filename, String extension){
		String ret = "";
		
		return ret;
	}

	public static void writeXMLToFile(String source, String target) throws XMLStreamException, IOException {
		// root elements
		// Document doc = loadXMLFromString(xml);
		/*
		 * File fXmlFile = new File("test.xml"); DocumentBuilderFactory
		 * dbFactory = DocumentBuilderFactory.newInstance(); DocumentBuilder
		 * dBuilder = dbFactory.newDocumentBuilder(); Document doc =
		 * dBuilder.parse(fXmlFile);
		 */
		String startTime = sdf.format(new Date());

		// System.out.println("File geparsed");

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		 InputStream in = new FileInputStream("test.xml");
		//InputStream in = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		FileOutputStream fos = new FileOutputStream(target);
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(fos);
		// XMLEventFactory eventFactory = XMLEventFactory.newInstance();

		// DatabaseLayer dbl = new DatabaseLayer();

		int counter = 0;
		String error = "";
		//List<String> queries = new ArrayList<String>();
		String test;

		while (eventReader.hasNext()) {
			counter++;
			XMLEvent event = eventReader.nextEvent();
			System.out.println(counter);
			eventWriter.add(event);
			try {

				//queries.add("INSERT INTO public.table1(id, name) VALUES ('" + replace(event.toString()) + "', '"+ "joehoe" + "');");
				if (event.toString().contains("<company>")) {
					//System.out.println(event.isStartElement());
					//System.out.println(event.isCharacters());
				}
				if (event.toString().contains("</company>")) {
					//System.out.println(event.isStartElement());
					//System.out.println(event.isCharacters());
				}
				if (event.toString().contains("<salary>")) {
					//System.out.println(event.isCharacters());
				}

			} catch (Error e) {
				error = e.toString();
			}

		}
		// dbl.create(queries);
		//eventWriter.flush();
		
		eventWriter.close();
		in.close();
		fos.close();
		//System.out.println("File saved! startime: " + startTime + " finished on: " + sdf.format(new Date())
		//		+ "Aantal loops: " + counter + " " + error);

		// test(xml);
		// String test = System.getProperty("user.dir");
		// System.out.println(test);
		// deleteFile("jo.xml");
	}

	public static void readXMLFromFile(String xml) throws FileNotFoundException, XMLStreamException {
		/*
		 * XMLInputFactory factory = XMLInputFactory.newInstance(); Reader
		 * fileReader = new FileReader(xml); XMLEventReader reader =
		 * factory.createXMLEventReader(fileReader);
		 */
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream("test.xml");
		// InputStream in = new
		// ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		XMLEventReader reader = inputFactory.createXMLEventReader(in);

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				StartElement element = (StartElement) event;
				System.out.println("Start Element: " + element.getName());

				Iterator iterator = element.getAttributes();
				while (iterator.hasNext()) {
					Attribute attribute = (Attribute) iterator.next();
					QName name = attribute.getName();
					String value = attribute.getValue();
					System.out.println("Attribute name/value: " + name + "/" + value);
				}
			}
			if (event.isEndElement()) {
				EndElement element = (EndElement) event;
				System.out.println("End element:" + element.getName());
			}
			if (event.isCharacters()) {
				Characters characters = (Characters) event;
				System.out.println("Text: " + characters.getData());
			}
		}
	}

	private static void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}

	private static String replace(String xml) {
		return xml.replaceAll("'", "&quot;").replaceAll("\"", "&quot;");
	}

	public static Document loadXMLFromStringWithDOM(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}

	public static void deleteFile(String fileName) {
		// Delete file
		File file = new File(fileName);
		if (file.delete()) {
			System.out.println(file.getAbsolutePath() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed." + file.getAbsolutePath());
		}
	}
}
