import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;



import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

import org.w3c.dom.Node;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	// WINDOW ELEMENTS
	private JButton btnSelecteerXmlBestand = new JButton("Selecteer xml bestand");
	private JLabel lblNogNietsGeselecteerd = new JLabel("Nog niets geselecteerd..");	  
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBox_1 = new JComboBox();
	private final JLabel lblNogNietsGeselecteerd_1 = new JLabel("Nog niets geselecteerd..");
	private final JLabel label = new JLabel("Nog niets geselecteerd..");	
	JButton deleteElements = new JButton("Verwijder attributen");
    private final JLabel lblBestandOpgeslagen = new JLabel("");
    private final JLabel lblSelecteerEenElement = new JLabel("Selecteer een element:");
    private final JLabel lblSelecteerEenAttribuut = new JLabel("Selecteer een attribuut:");
    private final JSeparator separator_5 = new JSeparator();
    private final JLabel label_1 = new JLabel("");
	
    // FIELDS
	String sourceLocation = null;	
    List<String> elementsInComboBox = new ArrayList<String>();
    List<String> attributesInComboBox = new ArrayList<String>();    
   	List<String> selectedElements = new ArrayList<String>();
    Element selectedElement = null;
    Element selectedAttribute = null;
    NodeList nodeList1= null;
    String selectedAttr = null;
    File fXmlFile = new File("");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = null;
    Document doc = null;
    NodeList nodeList=null;
    int versionNum = 0;
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Customize XML");
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		btnSelecteerXmlBestand.setBounds(10, 28, 166, 23);
		frame.getContentPane().add(btnSelecteerXmlBestand);		
		lblNogNietsGeselecteerd.setBounds(186, 32, 388, 14);
		frame.getContentPane().add(lblNogNietsGeselecteerd);	
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 62, 564, 2);
		frame.getContentPane().add(separator);			
		comboBox.setBounds(10, 100, 166, 20);
		frame.getContentPane().add(comboBox);		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 131, 564, 2);
		frame.getContentPane().add(separator_1);		
		comboBox_1.setBounds(10, 169, 166, 20);
		frame.getContentPane().add(comboBox_1);		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 200, 564, 2);
		frame.getContentPane().add(separator_2);	
		deleteElements.setBounds(10, 255, 166, 23);
		deleteElements.setEnabled(false);
		frame.getContentPane().add(deleteElements);
		lblNogNietsGeselecteerd_1.setBounds(186, 103, 388, 14);		
		frame.getContentPane().add(lblNogNietsGeselecteerd_1);
		label.setBounds(186, 172, 388, 14);		
		frame.getContentPane().add(label);
		lblBestandOpgeslagen.setBounds(186, 259, 388, 14);		
		frame.getContentPane().add(lblBestandOpgeslagen);
		lblSelecteerEenElement.setBounds(10, 75, 148, 14);		
		frame.getContentPane().add(lblSelecteerEenElement);
		lblSelecteerEenAttribuut.setBounds(10, 144, 148, 14);		
		frame.getContentPane().add(lblSelecteerEenAttribuut);
		separator_5.setBounds(168, 28, 0, 272);		
		frame.getContentPane().add(separator_5);
		label_1.setBounds(186, 286, 388, 14);		
		frame.getContentPane().add(label_1);
		elementsInComboBox.add("Selecteer element..");
		attributesInComboBox.add("Selecteer attribuut..");
		comboBox.setModel(new DefaultComboBoxModel(elementsInComboBox.toArray()));
		comboBox_1.setModel(new DefaultComboBoxModel(attributesInComboBox.toArray()));
		initializeListeners();
	}

	private void initializeListeners(){
		btnSelecteerXmlBestand.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JFrame parentFrame = new JFrame();
				File currentDirectory = new File("C:\\");
				 
				JFileChooser fileChooser = new JFileChooser(){
					@Override
					public void approveSelection(){
						if(!getSelectedFile().isFile()){
							JOptionPane.showMessageDialog(null, "Selecteer een bestand, geen map of folder.", "Alert", JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							super.approveSelection();
						}
					}
				};
				fileChooser.setCurrentDirectory(currentDirectory);
				fileChooser.setDialogTitle("Select a directory");   
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileHidingEnabled(false);
				fileChooser.showDialog(fileChooser, "Selecteer xml bestand");
				
				if(fileChooser.getSelectedFile().toString().contains(".xml")){
					lblNogNietsGeselecteerd.setText("");
					lblNogNietsGeselecteerd_1.setText("Selecteer een element:");
					label.setText("Selecteer een attribuut:");
					lblBestandOpgeslagen.setText("");
					emptyElements();
					emptyAttributes();
					lblNogNietsGeselecteerd.setText("Xml bestand geselecteerd: " + fileChooser.getSelectedFile().toString());
					sourceLocation = fileChooser.getSelectedFile().toString();					
					fillTags();
				}else {
					lblNogNietsGeselecteerd.setText("");
					lblNogNietsGeselecteerd.setText("Selecteer een xml bestand AUB: " + fileChooser.getSelectedFile().toString());
					sourceLocation = null; 					
				}
				
			}
			
		});
		
		deleteElements.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JFrame parentFrame = new JFrame();
				NodeList nList = doc.getElementsByTagName(selectedElement.getNodeName().toString()); 

	             for (int temp = 0; temp < nList.getLength(); temp++) {
	            	 Node nNode = nList.item(temp);                                 

	                    //System.out.println("\nCurrent Element :" + nNode.getNodeName());
	                    if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
	                           Element eElement = (Element) nNode;
	                           eElement.removeAttribute(selectedAttr);	                           
	                    }
	            }				
				try {
					TransformerFactory tf = TransformerFactory.newInstance();
		             Transformer transformer;
					transformer = tf.newTransformer();
					DOMSource source = new DOMSource(doc);	
		             Random r = new Random();	
		             int randomNum = r.nextInt();
		             //System.out.println();
		             String file = fXmlFile.getPath();
		             String[] parts = file.split("\\.");
		             StreamResult result = new StreamResult(parts[0] + versionNum + ".xml");	
		             transformer.transform(source, result);
		             lblBestandOpgeslagen.setText("Bestand opgeslagen: " + parts[0] + versionNum + ".xml");
		             //versionNum++;
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	         
				if(comboBox.getSelectedItem().toString() == "Selecteer element.."){

					lblNogNietsGeselecteerd_1.setText("Selecteer element: ");
				} else{
					lblNogNietsGeselecteerd_1.setText("Geselecteerd element: " + comboBox.getSelectedItem().toString());						
				}
				attributesInComboBox.clear();
				attributesInComboBox.add("Selecteer attribuut..");
				//emptyAttributes();
				label.setText("Selecteer attribuut:");
				label_1.setText("Succesvol attribuut ("+ selectedAttr +") verwijderd uit element (" + selectedElement.getNodeName().toString() + ")");
				fillAttributes();
				deleteElements.setEnabled(false);
			}
			
		});
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(comboBox.getSelectedItem().toString() == "Selecteer element.."){
					label.setText("Selecteer een attribuut:");
					lblNogNietsGeselecteerd_1.setText("Selecteer element: ");
				} else{
					lblNogNietsGeselecteerd_1.setText("Geselecteerd element: " + comboBox.getSelectedItem().toString());
					label.setText("Selecteer een attribuut:");
				}
				attributesInComboBox.clear();
				attributesInComboBox.add("Selecteer attribuut..");
				
				for(int i =0; i < nodeList.getLength(); i++){
					Element element = (Element)nodeList.item(i);
					if(element.getNodeName().toString() == comboBox.getSelectedItem().toString()){
						selectedElements.add(element.getNodeName().toString());
					}
				}

				fillAttributes();
				deleteElements.setEnabled(false);
				
			}
			
		});
		
		comboBox_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(comboBox_1.getSelectedItem() != "Selecteer attribuut.."){
					label.setText("Geselecteerd attribuut: " + comboBox_1.getSelectedItem().toString());
					selectedAttr = comboBox_1.getSelectedItem().toString();
					deleteElements.setEnabled(true);					
				}else{
					selectedAttr = null;
				}
			}
			
		});
	}
	
	private void emptyElements(){
		for (int i = 0; i < elementsInComboBox.size(); i++){
			if(i > 0){
				elementsInComboBox.remove(i);
			}
		}
		comboBox.setModel(new DefaultComboBoxModel(elementsInComboBox.toArray()));
	}
	
	private void emptyAttributes(){
		for (int i = 0; i < attributesInComboBox.size(); i++){
			
			if(i > 0){
				System.out.println("index: " + i + ", attribute: " + attributesInComboBox.get(i));
				attributesInComboBox.remove(i);
			}
			
		
		}
		comboBox_1.setModel(new DefaultComboBoxModel(attributesInComboBox.toArray()));
	}
	
	private void fillTags(){
		try {
			if(sourceLocation == null){
				//System.out.println("doe niks");
			}
			fXmlFile = new File(sourceLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);    
            
            doc.getDocumentElement().normalize();                   
            
            nodeList=doc.getElementsByTagName("*");
            for (int i=0; i<nodeList.getLength(); i++) 
            {
                // Get element
                Element element = (Element)nodeList.item(i);
                
                if(!elementsInComboBox.contains(element.getNodeName().toString())){
                	elementsInComboBox.add(element.getNodeName().toString());
                }
                //System.out.println(element.getNodeName());
            }    
            comboBox.setModel(new DefaultComboBoxModel(elementsInComboBox.toArray()));
            
		} catch (Exception e) {
            e.printStackTrace();
          }
	}
	
	private void fillAttributes(){
		try {
			if(sourceLocation == null){
				//System.out.println("doe niks");
			}
			
			List<Element> allElements = getElements(comboBox.getSelectedItem().toString());
			
			for(int y = 0; y < allElements.size(); y++){
				selectedElement = allElements.get(y);
				NamedNodeMap innerElmnt_gold_attr = selectedElement.getAttributes();
				for (int i = 0; i < innerElmnt_gold_attr.getLength(); ++i)
				{
				    Node attr = innerElmnt_gold_attr.item(i);
				    if(!attributesInComboBox.contains(attr.getNodeName().toString())){
				    	attributesInComboBox.add(attr.getNodeName().toString());
	                }
				    //System.out.println(attr.getNodeName());
				}
			}
		
            comboBox_1.setModel(new DefaultComboBoxModel(attributesInComboBox.toArray()));
            
		} catch (Exception e) {
            e.printStackTrace();
          }
	}
	
	private List<Element> getElements(String elementName){
		List<Element> myElement = new ArrayList<Element>();;
		for(int i =0; i < nodeList.getLength(); i++){
			Element element = (Element)nodeList.item(i);
			if(element.getNodeName().toString() == elementName){
				myElement.add(element);
			}
		}
		return myElement;
	}
}
