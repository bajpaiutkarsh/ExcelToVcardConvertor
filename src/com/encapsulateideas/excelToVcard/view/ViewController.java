package com.encapsulateideas.excelToVcard.view;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.encapsulateideas.excelToVcard.processing.Main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewController extends Application implements Initializable{
	public Stage primaryStage;
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
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = stage;
		FXMLLoader loader = new FXMLLoader();
		// Path to the FXML File
		final File file = new File(ViewController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String fxmlDocPath = file.getAbsolutePath().toString()+"/com/encapsulateideas/excelToVcard/view/View.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

		// Create the Pane and all Details
		root = (BorderPane) loader.load(fxmlStream);
		
		// Create the Scene
		Scene scene = new Scene(root);
		System.out.println(gpane);
		// Set the Scene to the Stage
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Excel File");
		
		stage.setScene(scene);
		// Set the Title to the Stage
		stage.setTitle("Excel to Vcard -- EncapsulateIdeas");
		// Display the Stage
		stage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
