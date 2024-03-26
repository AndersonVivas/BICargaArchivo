package ec.com.oncedev.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utilidades {
	
	public static boolean SinCaracteresEspeciales(String campo) {
		// Define la expresión regular para validar caracteres especiales
        String regex = "^[a-zA-Z0-9 ]*$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un objeto Matcher para buscar la coincidencia en el campo
        Matcher matcher = pattern.matcher(campo);

        // Comprueba si el campo contiene caracteres especiales
        if (matcher.matches()) {
           return true;
        } else {
        	System.out.println("Este campo " + campo + " Cuenta con caracteres especiales.");
        	return false;
        }
	}
	
	public static boolean SoloNumeros(String campo) {
		// Define la expresión regular para validar caracteres especiales
        String regex = "^-?\\d+(\\.\\d+)?$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un objeto Matcher para buscar la coincidencia en el campo
        Matcher matcher = pattern.matcher(campo);

        // Comprueba si el campo contiene caracteres especiales
        if (matcher.matches()) {
           return true;
        } else {
        	System.out.println("Este campo " + campo + " no son solo numeros.");
        	return false;
        }
	}
	public static String limpiarSimbolos(String cadena) {
        // Eliminar el símbolo $
        return cadena.replaceAll("\\$", "");
    }

    public static String limpiarEspacios(String cadena) {
        // Eliminar espacios
        return cadena.replaceAll("\\s", "");
    }

}