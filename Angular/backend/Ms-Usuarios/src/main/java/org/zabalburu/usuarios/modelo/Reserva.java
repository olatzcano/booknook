package org.zabalburu.usuarios.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="reservas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="idreserva")
	private Integer idReserva;
	@ManyToOne
	@JoinColumn(name = "idviaje")
	private Viaje viaje;
	@Column(name = "idvueloida")
	private Integer idVueloIda;
	@Column(name = "idvuelovuelta")
	private Integer idVueloVuelta;
	@Column(name = "idhabitacion")
	private Integer idHabitacion;
	@Column(name = "idinteres")
	private Integer idInteres;
	@Column(name="fechainicio")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fInicio;
	@Column(name="fechafin")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fFin;
	
	private Integer cantidad;
}