/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

/**
 *
 * @author Edgar
 */
public class Paso {
    
    private static enum Evento {
                                INICIO,
                                LLEGADA,
                                FIN_A,
                                FIN_B,
                                FIN_ESCUCHAR,
                                FIN_COMPRAR
                                }
    private static enum Actividad {
                                MIRAR,
                                CONSULTAR
                                }
    private static enum Consulta {
                                POR_COMPRA,
                                POR_ESCUCHA
                                }
    private static enum Decision {
                                COMPRAR,
                                NO_COMPRAR
                                }
    private static enum Estado {
                                LIBRE,
                                OCUPADO
                                }
    private static enum Cajero {
                                A,
                                B
                                }
    
    private long hora;
    private int rndLlegada;
    private long tEntreLleg;
    private long proxLlegada;
    
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
    private long finEscucha;
    private int rndDecision;
    private long finCompra;
    
    private Evento evento;
    private String eventoNumerado;
    private Evento menorEvento;
}
