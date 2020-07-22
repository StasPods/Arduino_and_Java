package sample;


import arduino.Arduino;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javax.comm.CommPortIdentifier;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb;

    @FXML
    private TextArea tgt;

    @FXML
    private Button botton3;

    @FXML
    private Button button1;

    @FXML
    private Button button2;
    @FXML
    private Button vih;

Arduino arduino = null;
boolean connected = false;
ArrayList<String> people = new ArrayList<String>();



    @FXML
    void initialize() {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
           portId.


        }


        botton3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String text= tgt.getText();
                if (tgt.getText().trim().equals("")) {
                    lb.setText("Введите порт");
                }else {
                    And(text);
                }
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String q1 = "q";
                char q = q1.charAt(0);
                arduino.serialWrite(q);
                action("VKL");
                people.add(arduino.serialRead());
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String e1 = "e";
                char e= e1.charAt(0);
                arduino.serialWrite(e);
                action("VIKL");
                people.add(arduino.serialRead());
            }
        });
        vih.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String e1 = "e";
                char e= e1.charAt(0);
                arduino.serialWrite(e);
                action("VIKL");
                for(String person : people){

                    action(person);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }

            }
        });




    }
    public void action(String number){
        lb.setText(number);
    }

    public void And(String port){

         arduino = new Arduino(port, 9600);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connected = arduino.openConnection();
        if(connected==true){
            lb.setText("Соединение установлено");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            botton3.setDisable(true);
        }
        if (connected!=true) {
            lb.setText("Соединение не установлено");
        }
    }

}


