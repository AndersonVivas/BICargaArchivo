package ec.com.oncedev.data.schedule;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import ec.com.oncedev.model.OrderTransacccion;
import ec.com.oncedev.service.ClienteService;
import ec.com.oncedev.service.LeerArchivoCSV;
import ec.com.oncedev.service.OrdenTransaccionService;
import ec.com.oncedev.thread.ThreadOrderTransacccion;
import ec.com.oncedev.util.Constantes;

@Singleton
@Startup
public class TareaProgramada implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6281105462057934529L;
	
	@Resource
	private TimerService timer;	
	
	@Inject
    private OrdenTransaccionService ordenTransaccionService;
	/**
	 * Este metodo es el que se lo canderiza para ejecutar la tarea programada 
	 */
	@Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
	private void arrancarCarga() {

		try {
			System.out.println("Tarea programada ejecutada.");
			List<OrderTransacccion> listaTransacciones = LeerArchivoCSV.lecturaArchivo();

			for (OrderTransacccion orderTransacccion : listaTransacciones) {
				ordenTransaccionService.register(orderTransacccion);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * En este metodo se maneja el registro en base de datos de las transacciones que obtenemos del documento CSV
	 * Este registo lo manejamos mediante hilos en este caso se utiliza una constante la cual determina cuantos hilos se quiere utilizar
	 * @param listaTransacciones
	 */
	private static void GuardarInformación (List<OrderTransacccion> listaTransacciones) {
		int numeroRegistros= listaTransacciones.size();
		int registrosPorHilo = numeroRegistros / Constantes.Numero_Hilos; 
	    int registrosRestantes = numeroRegistros % Constantes.Numero_Hilos;
	    
	    int indiceInicio = 0;
	        // Iterar sobre los hilos
	    for (int i = 0; i < Constantes.Numero_Hilos; i++) {
            int registrosParaEsteHilo = registrosPorHilo;
            if (registrosRestantes > 0) {
                registrosParaEsteHilo++;
                registrosRestantes--;
            }
            List<OrderTransacccion> registrosHilo = listaTransacciones.subList(indiceInicio, indiceInicio + registrosParaEsteHilo);	
            Runnable thread = new ThreadOrderTransacccion(registrosHilo);
            new Thread(thread).start();
            indiceInicio += registrosParaEsteHilo;

            
        }    
	}

}
