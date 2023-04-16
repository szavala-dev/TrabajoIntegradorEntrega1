package Domain;

import Enum.Resultado;

/**
 * Grupo 5 Mauricio Villalobos Sebastian Zavala
 *
 */
public class Pronostico {

	private Partido partido;
	private Equipo equipo;
	private Resultado resultado;

	public Pronostico(Partido partido, Equipo equipo, Resultado resultado) {

		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public int puntos() {
		if (partido.resultado(equipo).equals(resultado)) {
			return 1;
		}
		return 0;
	}

}
