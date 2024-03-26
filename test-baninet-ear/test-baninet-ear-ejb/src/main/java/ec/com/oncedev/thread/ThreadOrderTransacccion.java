package ec.com.oncedev.thread;
import java.util.List;

import javax.inject.Inject;

import ec.com.oncedev.model.OrderTransacccion;
import ec.com.oncedev.service.OrdenTransaccionService;

public class ThreadOrderTransacccion implements Runnable{

	private List<OrderTransacccion> registrosHilo;
	
	@Inject
    private OrdenTransaccionService ordenTransaccionService;
	
	@Override
	public void run() {
		try {
			for (OrderTransacccion orderTransacccion : registrosHilo) {			
					ordenTransaccionService.register(orderTransacccion);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ThreadOrderTransacccion(List<OrderTransacccion> registrosHilo) {
		super();
		this.registrosHilo = registrosHilo;
	}
	

}
