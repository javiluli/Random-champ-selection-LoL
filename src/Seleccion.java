/**
 *
 * @author Javier Delgado Rodriguez
 */
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class Seleccion.
 */
public class Seleccion implements Runnable {
	
	/** The numero anterior. */
	int numero_anterior = -1;

	/**
	 * Instantiates a new seleccion.
	 */
	public Seleccion() {
	}

	/** The a. */
	Main a;

	/**
	 * Instantiates a new seleccion.
	 *
	 * @param a the a
	 */
	public Seleccion(Main a) {
		this.a = a;
	}

	/**
	 * Permite verificar si el numero anterior generado es el mismo que el actual,
	 * en caso de que lo sea se devolvera un True, en caso de no ser el mismo
	 * devolvera False.
	 *
	 * @param n Posicion en el array para el boton seleccionado
	 * @return true En caso de que ambos numeros sean igual, tanto el numero como el
	 *         anterior a este
	 */
	public boolean replica(int n) {
		boolean repetir = true;

		if (numero_anterior == n)
			repetir = false;

		numero_anterior = n;
		return repetir;
	}

	/**
	 * Listado fotos.
	 *
	 * @param direccion_fotos the direccion fotos
	 * @return the string[]
	 */
	public String[] listadoFotos(String direccion_fotos) {
		File file = new File(direccion_fotos);
		String[] lista = file.list();
		return lista;
	}

	/**
	 * Texto nombre campeon.
	 *
	 * @param s the s
	 */
	public void textoNombreCampeon(String s) {
		s = s.substring(0, s.indexOf(".")).toUpperCase();
		s = s.substring(0).replace("_", " "); // Cambia un "_" por un espacio en blanbo
		s = s.substring(0).replace("#", "'");// Cambia un "#" por un apostrofo ingles
		s = s.substring(0).replace("&", ".");// Cambia un "&" por un punto
		a.getLblNombreChamp().setText(s);
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		a.setCambiarLinea(true);
		Animation an = new Animation();
		an.toBottom(a.getLblNombreChamp(), a.getLblFotos());
		String[] vs = listadoFotos(a.getDIRECCION_CARPETA_FOTOS());

		try {
			if (a.isUso_fichero())
				try {
					vs = Ficheros.leerFichero(a.getDireccion_fichero());
				} catch (IOException e) {
				}

			int n = 0, m = an.randomInt();
			for (int i = 0; i < m; i++) {
				n = an.randomInt(vs.length);
				a.getLblFotos().setIcon(new ImageIcon(a.getDIRECCION_CARPETA_FOTOS() + vs[n]));
				Thread.sleep(50);
			}

			textoNombreCampeon(vs[n]);
			a.setCambiarLinea(false);
			an.toTop(a.getLblNombreChamp(), a.getLblFotos());
		} catch (InterruptedException ie) {
		}
	}
}
