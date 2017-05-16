/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Edgar
 */
public class Paso {
    
    public static enum Evento {
                                INICIO,
                                LLEGADA,
                                FIN_A,
                                FIN_B,
                                FIN_ESCUCHAR,
                                FIN_COMPRAR
                                }
    public static enum Actividad {
                                MIRAR,
                                CONSULTAR
                                }
    public static enum Consulta {
                                POR_COMPRA,
                                POR_ESCUCHA
                                }
    public static enum Decision {
                                COMPRAR,
                                NO_COMPRAR
                                }
    public static enum Estado {
                                LIBRE,
                                OCUPADO
                                }
    public static enum Cajero {
                                A,
                                B
                                }
    
    private double hora;
    private int rndLlegada;
    private double tEntreLleg;
    private double proxLlegada;
    
    private double finA;
    private double finB;
    
    private int nroCliLlegada;
    private int nroCliFinA;
    private int nroCliFinB;
    private int nroCliFinEscucha;
    private int nroCliFinCompra;
    
    private Estado estadoA;
    private Estado estadoB;
    private int cola;
    private int rndActividad;
    private int rndTipoConsulta;
    private int rndEscucha;
    private double finEscucha;
    private int rndDecision;
    private double finCompra;
    
    private Evento evento;
    private String eventoNumerado;
    private Evento menorEvento;
    
    private String pEventoNumerado;
    private List<Double> eventosOrdenados = new ArrayList<Double>();
    private int mayorIndice;
    private List<Integer> indicesOrdenados = new ArrayList<Integer>();
    
    public void Paso(){
        
    }
    
    public void Paso(double hora, Evento evento){
        this.hora = hora;
        this.evento = evento;
    }
    
    public List<Double> getRelojMenorEvento(){
        
        if(proxLlegada > 0){
            eventosOrdenados.add(proxLlegada);
        }
        if(finA > 0){
            eventosOrdenados.add(finA);
        }
        if(finB > 0){
            eventosOrdenados.add(finB);
        }
        if(finEscucha > 0){
            eventosOrdenados.add(finEscucha);
        }
        if(finCompra > 0){
            eventosOrdenados.add(finCompra);
        }
        
        Collections.sort(eventosOrdenados);
        return eventosOrdenados;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }

    public int getRndLlegada() {
        return rndLlegada;
    }

    public void setRndLlegada(int rndLlegada) {
        this.rndLlegada = rndLlegada;
    }

    public double gettEntreLleg() {
        return tEntreLleg;
    }

    public void settEntreLleg(double tEntreLleg) {
        this.tEntreLleg = tEntreLleg;
    }

    public double getProxLlegada() {
        return proxLlegada;
    }

    public void setProxLlegada(double proxLlegada) {
        this.proxLlegada = proxLlegada;
    }

    public double getFinA() {
        return finA;
    }

    public void setFinA(double finA) {
        this.finA = finA;
    }

    public double getFinB() {
        return finB;
    }

    public void setFinB(double finB) {
        this.finB = finB;
    }

    public int getNroCliLlegada() {
        return nroCliLlegada;
    }

    public void setNroCliLlegada(int nroCliLlegada) {
        this.nroCliLlegada = nroCliLlegada;
    }

    public int getNroCliFinA() {
        return nroCliFinA;
    }

    public void setNroCliFinA(int nroCliFinA) {
        this.nroCliFinA = nroCliFinA;
    }

    public int getNroCliFinB() {
        return nroCliFinB;
    }

    public void setNroCliFinB(int nroCliFinB) {
        this.nroCliFinB = nroCliFinB;
    }

    public int getNroCliFinEscucha() {
        return nroCliFinEscucha;
    }

    public void setNroCliFinEscucha(int nroCliFinEscucha) {
        this.nroCliFinEscucha = nroCliFinEscucha;
    }

    public int getNroCliFinCompra() {
        return nroCliFinCompra;
    }

    public void setNroCliFinCompra(int nroCliFinCompra) {
        this.nroCliFinCompra = nroCliFinCompra;
    }

    public Estado getEstadoA() {
        return estadoA;
    }

    public void setEstadoA(Estado estadoA) {
        this.estadoA = estadoA;
    }

    public Estado getEstadoB() {
        return estadoB;
    }

    public void setEstadoB(Estado estadoB) {
        this.estadoB = estadoB;
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }

    public int getRndActividad() {
        return rndActividad;
    }

    public void setRndActividad(int rndActividad) {
        this.rndActividad = rndActividad;
    }

    public int getRndTipoConsulta() {
        return rndTipoConsulta;
    }

    public void setRndTipoConsulta(int rndTipoConsulta) {
        this.rndTipoConsulta = rndTipoConsulta;
    }

    public int getRndEscucha() {
        return rndEscucha;
    }

    public void setRndEscucha(int rndEscucha) {
        this.rndEscucha = rndEscucha;
    }

    public double getFinEscucha() {
        return finEscucha;
    }

    public void setFinEscucha(double finEscucha) {
        this.finEscucha = finEscucha;
    }

    public int getRndDecision() {
        return rndDecision;
    }

    public void setRndDecision(int rndDecision) {
        this.rndDecision = rndDecision;
    }

    public double getFinCompra() {
        return finCompra;
    }

    public void setFinCompra(double finCompra) {
        this.finCompra = finCompra;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEventoNumerado() {
        return eventoNumerado;
    }

    public void setEventoNumerado(String valor) {
        this.eventoNumerado = eventoNumerado + " " + valor;
    }

    public Evento getMenorEvento() {
        return menorEvento;
    }

    public void setMenorEvento(Evento menorEvento) {
        this.menorEvento = menorEvento;
    }

    public String getpEventoNumerado() {
        return pEventoNumerado;
    }

    public void setpEventoNumerado(String pEventoNumerado) {
        this.pEventoNumerado = pEventoNumerado;
    }

    public List<Double> getEventosOrdenados() {
        return eventosOrdenados;
    }

    public void setEventosOrdenados(List<Double> eventosOrdenados) {
        this.eventosOrdenados = eventosOrdenados;
    }

    public int getMayorIndice() {
        
        indicesOrdenados.add(nroCliFinA);
        indicesOrdenados.add(nroCliFinB);
        indicesOrdenados.add(nroCliFinCompra);
        indicesOrdenados.add(nroCliFinEscucha);
        
        Collections.sort(indicesOrdenados);
        return indicesOrdenados.get(3) + 1;
    }

    public void setMayorIndice(int mayorIndice) {
        this.mayorIndice = mayorIndice;
    }
    
    
    
}
