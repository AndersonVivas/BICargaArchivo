package ec.com.oncedev.service;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import ec.com.oncedev.model.OrderTransacccion;
import ec.com.oncedev.util.Utilidades;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LeerArchivoCSV {

/**
 * Metodo el cual lee un archivo csv del disco C:/	
 * @return
 */
public static List<OrderTransacccion> lecturaArchivo() {
		
	List<OrderTransacccion> listaTransacciones = new ArrayList<>();
		try {
			
            String filePath = "C:\\Archivo.csv";           
            
            File csvFile = new File(filePath);
            Reader reader = new FileReader(csvFile);            
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            
            
            for (CSVRecord csvRecord : csvParser) {
            	String valor = limpiarValor(csvRecord.get("Valor"));
            	String validarCampos= ValidarValorNombre(valor,csvRecord.get("Nombre") );  
            	System.out.println("valor----> " + valor);
            	System.out.println("validarCampos----> " + validarCampos);
            	listaTransacciones.add(new OrderTransacccion(Integer.valueOf(csvRecord.get("Secuecnial")), csvRecord.get("Cod"), csvRecord.get("Nombre"), csvRecord.get("Identificacion"), csvRecord.get("Valor"), csvRecord.get("Tipo"), validarCampos));
            }
            csvParser.close();           
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	
		return listaTransacciones;
	
	}
	private static String limpiarValor(String valor) {
		valor= Utilidades.limpiarSimbolos(valor);
		valor= Utilidades.limpiarEspacios(valor);
		return valor;		
	}
	private static String ValidarValorNombre(String valor,String Nombre) {
		if (Utilidades.SoloNumeros(valor) && Utilidades.SinCaracteresEspeciales(Nombre)) {
			return "N";
		}else {
			return "E";
		}
	}
	
}
