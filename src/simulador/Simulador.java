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
    
    
    
    public void crearPasos(double horaInicio, double horaFin){
        System.out.println("crearPasos!");
        
        List<Paso> lstPasos = new ArrayList<Paso>();
        pasos = new Pasos();
        paso = new Paso();
        
        paso.setHora(horaInicio);
        paso.setEvento(Evento.INICIO);
        paso.setRndLlegada(getGeneradorLlegada());
        paso.settEntreLleg(obtenerTiempoLLegada(paso.getRndLlegada()));
        paso.setProxLlegada(horaInicio + paso.gettEntreLleg());
        
        lstPasos.add(paso);
        //pasos.setLstPaso(lstPasos);  ////////////////////////////////////////SE SETEA PARA MOSTRAR EN PANTALLA
        //setPasos(pasos); ////////////////////////////////////////////////////SE SETEA PARA MOSTRAR EN PANTALLA
        
        double horaActual;
        horaActual = horaInicio;
        int indice = 0;
        int indiceCliLlegada = 1;
        Paso pasoAnt;
        Paso pasoActual;
        
        while(horaActual < horaFin){
            pasoActual = new Paso();
            pasoAnt = lstPasos.get(indice);

            pasoActual.setHora(pasoAnt.getRelojMenorEvento());
            pasoActual.setEvento(pasoAnt.getMenorEvento());
            
            switch(pasoActual.getEvento()) {
                case LLEGADA: 
                    pasoActual.setNroCliLlegada(indiceCliLlegada);
                    pasoActual.setEventoNumerado(indiceCliLlegada);
                    pasoActual.setRndLlegada(getGeneradorLlegada());
                    pasoActual.settEntreLleg(obtenerTiempoLLegada(pasoActual.getRndLlegada()));
                    pasoActual.setProxLlegada(pasoActual.getHora()+pasoActual.gettEntreLleg());
                    
                    pasoActual.setRndActividad(getGeneradorActividad());
                    pasoActual.setTipoActividad(obtenerActividad(pasoActual.getRndActividad()));
                    
                    if(pasoActual.getTipoActividad().equals(Actividad.MIRAR)){//NO HACE NADA, YA QUE MIRA Y SE VA
                        
                    }else{
                        if(pasoAnt.getEstadoA().equals(Estado.LIBRE)){//ATIENDE A
                            pasoActual.setNroCliFinA(indiceCliLlegada);
                            pasoActual.setEstadoA(Estado.OCUPADO);
                            pasoActual.setFinA(pasoActual.getHora()+2);//SIEMPRE SE TARDA 2 MIN
                            
                            pasoActual.setRndTipoConsulta(getGeneradorTipoConsulta());
                            pasoActual.setTipoConsulta(pasoActual.getTipoConsulta());
                            
                            if(pasoActual.getTipoConsulta().equals(Consulta.POR_COMPRA)){//SE VA Y CHAU
                                pasoActual.setFinCompra(pasoActual.getFinA());
                            }else{//ESCUCHAR
                                pasoActual.setTiempoEscucha(obtenerTiempoEscucha());
                                pasoActual.setFinEscucha(pasoActual.getHora()+pasoActual.getTiempoEscucha());
                                
                                pasoActual.setRndDecision(getGeneradorDecision());
                                pasoActual.setDecision(obtenerDecision(pasoActual.getRndDecision()));
                                
                                if(pasoActual.getDecision().equals(Decision.NO_COMPRAR)){//SE VA Y CHAU
                                    
                                }else{//VA A COMPRAR
                                    pasoActual.setComprarDDEscuchar(pasoActual.getFinEscucha());
                                }
                                
                            }
                        
                        }else if (pasoAnt.getEstadoB().equals(Estado.LIBRE)) {//ATIENDE B

                        }else{//COLA

                        }
                    }
                    
                    //BAJAR EL RESTO DE LOS EVENTOS COMO ESTAN EN EL PASO ANTERIOR
                     break;
                            
                case FIN_A: 
                    break;
                
                case FIN_B: 
                    break;
                
                case FIN_ESCUCHAR: 
                    break;
                
                case FIN_COMPRAR: 
                    break;
                
                case COMPRAR_DD_ESCUCHAR: 
                    break;
            }
                    
        }
        
    }

    public int getGeneradorLlegada() {
        int a = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        System.out.println("getGeneradorLlegada: " + a);
        return a;
    }
    
    public int getGeneradorActividad() {
        int a = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        System.out.println("getGeneradorActividad: " + a);
        return a;
    }
    public String obtenerActividad(int rnd){
        if(rnd <= 80){
            return Actividad.CONSULTAR.toString();
        }else{
            return Actividad.MIRAR.toString();
        }
    }
    
    public int getGeneradorTipoConsulta() {
        int a = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        System.out.println("getGeneradorTipoConsulta: " + a);
        return a;
    }
    public String obtenerTipoConsulta(int rnd){
        if(rnd <= 40){
            return Consulta.POR_COMPRA.toString();
        }else{
            return Consulta.POR_ESCUCHA.toString();
        }
    }
    
    public double obtenerTiempoEscucha() {
        int max=5, min=3;
        double a = ThreadLocalRandom.current().nextDouble(max + 1 - min) + min;
        System.out.println("obtenerTiempoEscucha: " + a);
        return a;
    }
    
    public int getGeneradorDecision() {
        int a = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        System.out.println("getGeneradorDecision: " + a);
        return a;
    }
    public String obtenerDecision(int rnd){
        if(rnd <= 30){
            return Decision.COMPRAR.toString();
        }else{
            return Decision.NO_COMPRAR.toString();
        }
    }  
    
    public double obtenerTiempoLLegada(int rnd){
        //calcular
        double a = (double) rnd / 100 * 4;
        System.out.println("TiempoLLegada: " + a);
        return a;
    }
    
    
    
}
