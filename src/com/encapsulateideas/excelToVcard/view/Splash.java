package com.encapsulateideas.excelToVcard.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.encapsulateideas.excelToVcard.processing.Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash extends Application implements Initializable{
	public Stage primaryStage;
	@FXML
	public StackPane stackRoot;
	@FXML
	public ImageView splash;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("splashScreen.fxml"));
		/*FXMLLoader loader = new FXMLLoader();
		// Path to the FXML File
		
		final File file = new File(ViewController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String fxmlDocPath = file.getAbsolutePath().toString()+"bin/com/encapsulateideas/excelToVcard/view/splashScreen.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		loader.setLocation(getClass().getResource("/splashScreen.fxml"));*/
		// Create the Pane and all Details
		stackRoot = (StackPane) loader.load();
		
	
		Scene scene = new Scene(stackRoot);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.initStyle(StageStyle.UNDECORATED);
		// Display the Stage
		stage.show();
	}
public static void main(String[] args) {
	Application.launch(args);
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	/*final File file = new File(ViewController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	String imagePath = file.getAbsolutePath().toString()+"bin/com/encapsulateideas/excelToVcard/view/banner.png";
	splash.setImage(new Image("file:"+imagePath));*/
	new ViewController().start();
	
}



class ViewController extends Thread {
	
	public File excelFile = null;
	public File vcardFile = null;
	@FXML
	public BorderPane root;
	@FXML
	public GridPane gpane;
	@FXML
	public Label uploadExcelLabel;
	@FXML
	public Label saveVcardLabel;
	@FXML
	public Text successmsg;
	@FXML
	public Text errormsg;
	public FileChooser fileChooser = null;
	@FXML
	public TextField nameCell;
	@FXML
	public TextField numberCell;
	@Override
	public void run() {
		try{
			Thread.sleep(5000);
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						primaryStage = new Stage();
						// TODO Auto-generated method stub
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("View.fxml"));
/*						// Path to the FXML File
						final File file = new File(ViewController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
						String fxmlDocPath = file.getAbsolutePath().toString()+"bin/com/encapsulateideas/excelToVcard/view/View.fxml";
						FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
*/
						// Create the Pane and all Details
						root = (BorderPane) loader.load();
						
						// Create the Scene
						Scene scene = new Scene(root);
						System.out.println(gpane);
						// Set the Scene to the Stage
						fileChooser = new FileChooser();
						fileChooser.setTitle("Open Excel File");
						
						primaryStage.setScene(scene);
						// Set the Title to the Stage
						primaryStage.setTitle("Excel to Vcard -- EncapsulateIdeas");
						// Display the Stage
						primaryStage.show();
						stackRoot.getScene().getWindow().hide();
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			});
			
			
	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void addFile() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)","*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        excelFile = fileChooser.showOpenDialog(primaryStage);
        uploadExcelLabel.setText("Excel File Uploaded Successfully");
	}
	@FXML
	public void selectVcard(){
		FileChooser fileChooser = new FileChooser();
		  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("VCARD files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        vcardFile = fileChooser.showSaveDialog(primaryStage);
        
        if(vcardFile != null){
            SaveFile(vcardFile);
        }
        saveVcardLabel.setText("Vcard File created, Now export contact from excel to vcard");
	}
	private void SaveFile(File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
         
    }
	@FXML
	public void startConverting(){
		try{
		if(Main.convert(excelFile, vcardFile, Integer.parseInt(nameCell.getText()), Integer.parseInt(numberCell.getText()))){
			successmsg.setText("Contacts Exported successfully");
			errormsg.setText(null);
		}
		else{
			successmsg.setText(null);
			errormsg.setText("Something went wrong. please contact EncapsulateIdeas support");
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			successmsg.setText(null);
			errormsg.setText("Something went wrong.Contact EncapsulateIdeas support");
			e.printStackTrace();
		}
	}
}



}
