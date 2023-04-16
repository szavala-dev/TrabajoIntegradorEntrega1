package Domain;

import java.util.List;
import Enum.Resultado;

/**
 * Grupo 5 // Mauricio Villalobos // Sebastian Zavala
 *
 */

public class Ronda {
	private int id;
	private List<Partido> partidos;
	private String nro;

	public Ronda(int id, List<Partido> partidos2, String unNro) {
		this.id = id;
		this.partidos = partidos2;
		this.nro = unNro;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public int calcularPuntos(List<Pronostico> pronosticos) {
		int puntos = 0;

		for (Partido partido : this.partidos) {
			for (Pronostico pronostico : pronosticos) {
				if (partido.getId() == pronostico.getPartido().getId()) {
					Equipo equipoPronostico = pronostico.getEquipo();
					Resultado resultadoPartido = partido.resultado(equipoPronostico);
					if (resultadoPartido == pronostico.getResultado()) {
						puntos += pronostico.puntos();
					}
				}
			}
		}

		return puntos;
	}

}