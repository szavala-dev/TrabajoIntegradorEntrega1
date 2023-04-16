package Programa;

/**
 * Grupo 5
 * Mauricio Villalobos
 * Sebastian Zavala
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import Domain.Equipo;
import Domain.Partido;
import Domain.Pronostico;
import Domain.Ronda;
import Enum.Resultado;

public class Puntaje {
	/**
	 * @param args "C:\\Users\\feder\\eclipse-workspace\\ar.edu.utn.ti.e1\\resultados.csv"
	 *             "C:\\Users\\feder\\eclipse-workspace\\ar.edu.utn.ti.e1\\pronostico.csv"
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Por favor, proporcione las rutas a los archivos de resultados y pronósticos.");
			return;
		}

		Path rutaResultados = Paths.get(args[0]);
		Path rutaPronosticos = Paths.get(args[1]);

		List<Partido> partidos = leerResultados(rutaResultados);
		Ronda ronda = new Ronda(1, partidos, "1");
		List<Pronostico> pronosticos = leerPronosticos(rutaPronosticos, partidos);
		int totalDePuntos = ronda.calcularPuntos(pronosticos);

		System.out.println("Puntaje = " + totalDePuntos);
	}

	public static List<Partido> leerResultados(Path rutaResultados) {
		List<Partido> partidos = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(rutaResultados, StandardCharsets.UTF_8)) {
			reader.readLine();
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] partes = linea.split(";");
				int id = Integer.parseInt(partes[0]);
				Equipo equipo1 = new Equipo(partes[1]);
				int golesEquipo1 = Integer.parseInt(partes[2]);
				int golesEquipo2 = Integer.parseInt(partes[3]);
				Equipo equipo2 = new Equipo(partes[4]);

				Partido partido = new Partido(id, equipo1, equipo2, golesEquipo1, golesEquipo2);
				partidos.add(partido);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return partidos;
	}

	public static List<Pronostico> leerPronosticos(Path rutaPronosticos, List<Partido> partidos) {
		List<Pronostico> pronosticos = new ArrayList<>();

		try (BufferedReader read = Files.newBufferedReader(rutaPronosticos, StandardCharsets.UTF_8)) {
			read.readLine();
			String linea;
			while ((linea = read.readLine()) != null) {
				String[] partes = linea.split(";");
				int id = Integer.parseInt(partes[0]);
				Partido partido = buscarPartidoPorId(partidos, id);

				if (partido == null) {
					System.out.println("No se encontró el partido con ID: " + id);
					continue;
				}

				String gana1 = partes[2];
				String empatan = partes[3];
				Resultado resultado;
				Equipo equipo;
				if (gana1.equals("x")) {
					equipo = partido.getEquipo1();
					resultado = Resultado.GANADOR;
				} else if (empatan.equals("x")) {
					equipo = partido.getEquipo1();
					resultado = Resultado.EMPATE;
				} else {
					equipo = partido.getEquipo2();
					resultado = Resultado.GANADOR;
				}

				Pronostico pronostico = new Pronostico(partido, equipo, resultado);
				pronosticos.add(pronostico);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pronosticos;
	}

	public static Partido buscarPartidoPorId(List<Partido> partidos, int id) {
		for (Partido partido : partidos) {
			if (partido.getId() == id) {
				return partido;
			}
		}
		return null;
	}

}