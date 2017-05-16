/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import static simulador.Paso.*;

/**
 *
 * @author Edgar
 */
public class Simulador extends Application {
    
    private Paso paso;
    private Pasos pasos;

    public Pasos getPasos() {
        return pasos;
    }

    public void setPasos(Pasos pasos) {
        this.pasos = pasos;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //SIMULAR
        crearPasos(0, 600);
        
        //BOTON
        /*Button btn = new Button();
        btn.setText("Simular");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                crearPasos(0, 600);
                

            }
        });*/
        
        //TABLA
        TableView<Paso> tv = new TableView<Paso>();
        ObservableList<Paso> people = FXCollections.observableArrayList(getPasos().getLstPaso());
        TableColumn<Paso, String> typeCol = new TableColumn<>("evento");
        typeCol.setCellValueFactory(
            new PropertyValueFactory<>("evento")
        );
        tv.getColumns().add(typeCol);
        tv.setItems(people);
        
        StackPane root = new StackPane();
        root.getChildren().add(tv);
        //root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("SuperSimulador");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    public void crearPasos(long horaInicio, long horaFin){
        System.out.println("crearPasos!");
        
        Pasos pasos = new Pasos();
        Paso paso = new Paso();
        paso.setHora(horaInicio);
        paso.setEvento(Evento.INICIO);
        paso.setRndLlegada(getGeneradorLlegada());
        paso.settEntreLleg(obtenerTiempoLLegada(paso.getRndLlegada()));
        paso.setProxLlegada(horaInicio + paso.gettEntreLleg());
        System.out.println("setProxLlegada: " + paso.getProxLlegada());
        
        List<Paso> lstPasos = new ArrayList<Paso>();
        lstPasos.add(paso);
        pasos = new Pasos();
        pasos.setLstPaso(lstPasos);
        setPasos(pasos);
        
    }

    public int getGeneradorLlegada() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

    public double obtenerTiempoLLegada(int rnd){
        //calcular
        System.out.println("rnd: " + rnd);
        double a = (double) rnd / 100 * 4 + 5;
        //double a = (double) rnd/10000;
        System.out.println("a: " + a);
        return a;
    }
    
    
    
}
