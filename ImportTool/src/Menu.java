import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;

public class Menu extends JFrame {
	// Separators
	private JSeparator separator = new JSeparator();
	private JSeparator separator_1 = new JSeparator();
	private JSeparator separator_2 = new JSeparator();
	private JSeparator separator_3 = new JSeparator();
	private JSeparator separator_4 = new JSeparator();
	private JSeparator separator_5 = new JSeparator();
	
	// Selectable variables
	private String sourceLocation = null;
	private String importLocation = "D:\\dest\\";
	private String[] folderClassArray = {"Selecteer folderklasse"};
	private String[] documentClassArray = {"Selecteer documentklasse"};
	private String[] importArray = {"Selecteer importlocatie"};
	
	// Buttons and labels	
	private JPanel contentPane;
	private JButton selectSourceLocationBtn = new JButton("Selecteer bronlocatie");
	private JButton selectLoggingLocationBtn = new JButton("Wijzig logging");
	private JComboBox selectFolderClassComboBox = new JComboBox(folderClassArray);
	private JComboBox selectDocumentClassComboBox = new JComboBox(documentClassArray);
	private JComboBox selectImportLocationComboBox = new JComboBox(importArray);
	private static JLabel sourceLocationLabel = new JLabel("Nog niet geselecteerd..");
	private JButton selectFeaturesBtn = new JButton("Voeg kenmerken toe");
	private JLabel loggingLocationLabel = new JLabel("Nog niet geselecteerd..");
	private JLabel folderClassLocationLabel = new JLabel("Nog niet geselecteerd..");
	private JLabel documentClassLocationLabel = new JLabel("Nog niet geselecteerd..");
	private JLabel featuresLabel = new JLabel("Nog niet geselecteerd..");
	private JLabel importLocationLabel = new JLabel("Nog niet geselecteerd..");
	private JButton startImportBtn = new JButton("Start import");
	private JButton planImportBtn = new JButton("Plan import");
	
	// ProgressBar
	private static JProgressBar progressBar = new JProgressBar();
	
	// Create frame
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 496);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		selectSourceLocationBtn.setBounds(10, 40, 156, 23);
		contentPane.add(selectSourceLocationBtn);
		
		selectLoggingLocationBtn.setBounds(10, 95, 156, 23);
		contentPane.add(selectLoggingLocationBtn);		
		
		selectFolderClassComboBox.setBounds(10, 153, 156, 20);
		contentPane.add(selectFolderClassComboBox);		
		
		selectDocumentClassComboBox.setBounds(10, 208, 156, 20);
		contentPane.add(selectDocumentClassComboBox);
		
		selectImportLocationComboBox.setBounds(10, 318, 156, 20);
		contentPane.add(selectImportLocationComboBox);
		
		selectFeaturesBtn.setBounds(10, 262, 156, 23);
		contentPane.add(selectFeaturesBtn);
		
		separator.setBounds(10, 74, 616, 2);
		contentPane.add(separator);
		
		separator_1.setBounds(10, 129, 616, 2);
		contentPane.add(separator_1);
		
		separator_2.setBounds(10, 184, 616, 2);
		contentPane.add(separator_2);
		
		separator_3.setBounds(10, 239, 616, 2);
		contentPane.add(separator_3);
		
		separator_4.setBounds(10, 296, 616, 2);
		contentPane.add(separator_4);
		
		separator_5.setBounds(10, 349, 616, 2);
		contentPane.add(separator_5);
		
		sourceLocationLabel.setBounds(258, 44, 368, 14);
		contentPane.add(sourceLocationLabel);
		
		loggingLocationLabel.setBounds(258, 99, 368, 14);
		contentPane.add(loggingLocationLabel);
		
		folderClassLocationLabel.setBounds(258, 156, 368, 14);
		contentPane.add(folderClassLocationLabel);
		
		documentClassLocationLabel.setBounds(258, 211, 368, 14);
		contentPane.add(documentClassLocationLabel);
		
		featuresLabel.setBounds(258, 266, 368, 14);
		contentPane.add(featuresLabel);
		
		importLocationLabel.setBounds(258, 321, 368, 14);
		contentPane.add(importLocationLabel);
		
		startImportBtn.setBounds(509, 362, 117, 36);
		contentPane.add(startImportBtn);
		
		planImportBtn.setBounds(509, 409, 117, 36);
		contentPane.add(planImportBtn);
		
		progressBar.setBounds(10, 384, 156, 14);		
		contentPane.add(progressBar);
				
		initializeListeners();
	}
	
	// Initialize listeners 
	public void initializeListeners(){
		selectSourceLocationBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame parentFrame = new JFrame();
				File currentDirectory = new File("C:\\");
				 
				JFileChooser fileChooser = new JFileChooser(){
					@Override
					public void approveSelection(){
						if(getSelectedFile().isFile()){
							JOptionPane.showMessageDialog(null, "Selecteer een map/folder, geen apart bestand!", "Alert", JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							super.approveSelection();
						}
					}
				};
				fileChooser.setCurrentDirectory(currentDirectory);
				fileChooser.setDialogTitle("Select a directory");   
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setFileHidingEnabled(false);
				fileChooser.showDialog(fileChooser, "Selecteer map");
				
				if(fileChooser.getSelectedFile().toString().contains(".")){
					sourceLocationLabel.setText("Selecteer een map/folder aub..");
					sourceLocation = null;					
				}else {
					sourceLocationLabel.setText("Bronlocatie geselecteerd: " + fileChooser.getSelectedFile().toString());
					sourceLocation = fileChooser.getSelectedFile().toString(); 					
				}
				
			}
			
		});

		selectLoggingLocationBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
		});
		
		selectFolderClassComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
		});
		
		selectDocumentClassComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
		});
		
		selectImportLocationComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
		});
		
		selectFeaturesBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
		});
		
		startImportBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(sourceLocation != null){
					File source = new File(sourceLocation);
					File dest = new File(importLocation);
					try {
						copyFolder(source, dest);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Selecteer eerst alle velden!", "Alert", JOptionPane.INFORMATION_MESSAGE);
				}
				sourceLocation = null;
			}
			
		});
		
		planImportBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("plan import");
			}
			
		});
		
	}
	
	public void copyFolderToFileNet() throws IOException{
		File src = new File(sourceLocation);
		InputStream in = new FileInputStream(src);
		
		
		String user = "user:password";
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
		String path = "smb://my_machine_name/D/MyDev/test.txt";
		SmbFile sFile = new SmbFile(path, auth);
		SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
		
		
		byte[] buffer = new byte[1024];
		int length;

        //copy the file content in bytes
        while ((length = in.read(buffer)) > 0){
        	sfos.write(buffer, 0, length);
        }
        in.close();
        sfos.close();
	}
	
	public static void copyFolder(File src, File dest) throws IOException{
		if(src.isDirectory()){                
			//progressBar.setValue(10);
	       //if directory not exists, create it
	       if(!dest.exists()){
	          dest.mkdir();
	          System.out.println("Directory copied from "+ src + "  to " + dest);
	
	       }
	       
            //list all the directory contents
           String files[] = src.list();
           int progress = 0;
           
           for (String file : files) {
        	   //progress = progress + 40;
        	   //progressBar.setValue(progress);
              //construct the src and dest file structure
              File srcFile = new File(src, file);
              File destFile = new File(dest, file);
              //recursive copy
              copyFolder(srcFile,destFile);
           }
		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;

            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                 out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);

            }
		//progressBar.setValue(100);
		//progressBar.setValue(0);
		//sourceLocationLabel.setText("Nog niets geselecteerd");
	}
}
