package DataManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Cantante;

public class Filetxt implements DataManager {

	File bbddCantante = new File("cantantes.txt");

	File bbddGenero = new File("generos.txt");

	private ObservableList<Cantante> cantantes;

	public ObservableList<Cantante> transicionDatos() {

		cantantes = FXCollections.observableArrayList();
		FileReader leerbbddCantante;

		try {

			leerbbddCantante = new FileReader(bbddCantante);
			BufferedReader brCantante = new BufferedReader(leerbbddCantante);
			String lineaCantante = brCantante.readLine();

			while (lineaCantante != null) {
				String[] auxC = lineaCantante.split(", ");

				FileReader leerbbddGenero = new FileReader(bbddGenero);
				BufferedReader brGenero = new BufferedReader(leerbbddGenero);
				String lineaGenero = brGenero.readLine();
				String[] auxG = lineaGenero.split(", ");
				String gen = null;
				while (lineaGenero != null) {
					if (auxC[4].equals(auxG[0])) {
						gen = auxG[1];
					}
					if (auxC[4].equals(auxG[0])) {
						lineaGenero = null;
					} else {
						lineaGenero = brGenero.readLine();
						auxG = lineaGenero.split(", ");
					}
				}

				Cantante sell = new Cantante(Integer.parseInt(auxC[0]), auxC[1], auxC[2], auxC[3], gen);
				cantantes.add(sell);
				lineaCantante = brCantante.readLine();
			}
			brCantante.close();
			leerbbddCantante.close();
			return cantantes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void insercionDatos(Cantante cantante) {
		String nombre = cantante.getNombre();
		String fechaNac = cantante.getNacimiento();
		String nacionalidad = cantante.getNacionalidad();
		int genero = 0;

		FileReader leerbbddGenero;
		try {
			leerbbddGenero = new FileReader(bbddGenero);
			BufferedReader brGenero = new BufferedReader(leerbbddGenero);
			String lineaGenero = brGenero.readLine();
			String[] auxG = lineaGenero.split(", ");
			while (lineaGenero != null) {
				FileWriter p = new FileWriter(bbddCantante, true);
				PrintWriter pr = new PrintWriter(p, true);
				if (cantante.getGenero().equals(auxG[1])) {
					genero = Integer.parseInt(auxG[0]);
					pr.print(this.darID() + ", ");
					pr.print(nombre + ", ");
					pr.print(fechaNac + ", ");
					pr.print(nacionalidad + ", ");
					pr.println(genero);
				}
				if (cantante.getGenero().equals(auxG[1])) {
					lineaGenero = null;
				} else {
					lineaGenero = brGenero.readLine();
					auxG = lineaGenero.split(", ");
				}
				pr.close();
				p.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void borradoDatos(Cantante cantante) {
		try {
			for (int i = 0; i < cantantes.size(); i++) {
				if (cantantes.get(i).getID() == (cantante.getID()))
					this.cantantes.remove(i);
			}

			FileWriter p = new FileWriter(bbddCantante);
			PrintWriter pr = new PrintWriter(p, true);
			for (int x = 0; x < cantantes.size(); x++) {
				String nombre = cantantes.get(x).getNombre();
				String fechaNac = cantantes.get(x).getNacimiento();
				String nacionalidad = cantantes.get(x).getNacionalidad();
				
				FileReader leerbbddGenero = new FileReader(bbddGenero);
				BufferedReader brGenero = new BufferedReader(leerbbddGenero);
				String lineaGenero = brGenero.readLine();
				String[] auxG = lineaGenero.split(", ");
				String genero = null;
				
				while (lineaGenero != null) {
					if (cantantes.get(x).getGenero().equals(auxG[1])) {
						genero = auxG[0];
					}
					if (cantantes.get(x).getGenero().equals(auxG[1])) {
						lineaGenero = null;
					} else {
						lineaGenero = brGenero.readLine();
						auxG = lineaGenero.split(", ");
					}
				}
				
				pr.print("1" + ", ");
				pr.print(nombre + ", ");
				pr.print(fechaNac + ", ");
				pr.print(nacionalidad + ", ");
				pr.println(genero);
			}
			pr.close();
			p.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public ObservableList<String> muestraUno(Cantante selectedItem) {

		cantantes = FXCollections.observableArrayList();
		FileReader leerbbddCantante;
		try {
			leerbbddCantante = new FileReader(bbddCantante);
			BufferedReader brCantante = new BufferedReader(leerbbddCantante);
			String lineaCantante = brCantante.readLine();
			ObservableList<String> datosCantante = FXCollections.observableArrayList();
			while (lineaCantante != null) {
				String[] auxC = lineaCantante.split(", ");
				if (selectedItem.getID() == Integer.parseInt(auxC[0])) {
					for (int x = 0; x < auxC.length; x++) {
						if (x != auxC.length - 1) {
							datosCantante.add(auxC[x]);
						} else {
							FileReader leerbbddGenero = new FileReader(bbddGenero);
							BufferedReader brGenero = new BufferedReader(leerbbddGenero);
							String lineaGenero = brGenero.readLine();
							String[] auxG = lineaGenero.split(", ");
							while (lineaGenero != null) {
								if (auxC[x].equals(auxG[0])) {
									datosCantante.add(auxG[1]);
								}
								if (auxC[x].equals(auxG[0])) {
									lineaGenero = null;
								} else {
									lineaGenero = brGenero.readLine();
									auxG = lineaGenero.split(", ");
								}
							}
						}
					}
				}
				lineaCantante = brCantante.readLine();

			}
			brCantante.close();
			leerbbddCantante.close();
			return datosCantante;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void borradoTabla() {
		try {
			FileWriter p = new FileWriter(bbddCantante);
			PrintWriter pr = new PrintWriter(p, true);
			pr.print("");
			pr.close();
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<Cantante> exportarDatos() {
		return this.transicionDatos();
	}

	public void importarDatos(ObservableList<Cantante> arrayCantantes) {

		this.borradoTabla();

		for (int x = 0; x < arrayCantantes.size(); x++) {
			this.insercionDatos(arrayCantantes.get(x));
		}
	}

	public int darID() throws IOException {
		cantantes = FXCollections.observableArrayList();
		FileReader leerArchivo = new FileReader(bbddCantante);
		BufferedReader br = new BufferedReader(leerArchivo);
		String linea = br.readLine();
		int x = 0;
		while (linea != null) {
			String[] aux = linea.split(", ");
			if (x < Integer.parseInt(aux[0])) {
				x = Integer.parseInt(aux[0]);
			}
			linea = br.readLine();
		}
		br.close();
		leerArchivo.close();
		return x + 1;
	}

}
