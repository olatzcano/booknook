package org.zabalburu.usuarios.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zabalburu.usuarios.dao.ReservaImplementation;
import org.zabalburu.usuarios.dao.UsuarioImplementation;
import org.zabalburu.usuarios.dao.ViajesImplementation;
import org.zabalburu.usuarios.dto.ReservaDTO;
import org.zabalburu.usuarios.dto.UsuarioDTO;
import org.zabalburu.usuarios.dto.ViajeDTO;
import org.zabalburu.usuarios.modelo.Reserva;
import org.zabalburu.usuarios.modelo.Usuario;
import org.zabalburu.usuarios.modelo.Viaje;
import org.zabalburu.usuarios.util.Encriptar;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioImplementation udao;

	@Autowired
	private ReservaImplementation rdao;

	@Autowired
	private ViajesImplementation vdao;

	@GetMapping("/usuarios")
	public List<Usuario> getUsuarios() {
		return udao.getUsuarios();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
		Usuario u = udao.getUsuario(id);
		if (u == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(u);
		}
	}

	@GetMapping("/usuarios/login/{usuario}/{password}")
	public ResponseEntity<Usuario> login(@PathVariable String usuario, @PathVariable String password) {
		Encriptar e = new Encriptar();
		Usuario u = udao.getUsuario(usuario);
		if (u == null) {
			return ResponseEntity.notFound().build();
		} else {
			String hash = e.getHash(password, u.getSalto());
			u = udao.login(usuario, hash);
			if (u != null) {
				return ResponseEntity.ok(u);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> añadirUsuario(@RequestBody UsuarioDTO udto) throws URISyntaxException {
		if (udao.getUsuario(udto.getUsuario()) == null) {
			Encriptar e = new Encriptar();
			String salto = e.getSalto(10);
			String h = e.getHash(udto.getPassword(), salto);
			Usuario u = new Usuario();
			u.setUsuario(udto.getUsuario());
			u.setHash(h);
			u.setSalto(salto);
			u.setDireccion(udto.getDireccion());
			u.setNombre(udto.getNombre());
			u.setApellido(udto.getApellido());
			u.setTelefono(udto.getTelefono());
			u.setEmail(udto.getEmail());
			u.setAdmin(false);
			u = udao.añadirUsuario(u);
			return ResponseEntity.created(new URI("http://localhost:8082/usuarios/" + u.getIdUsuario())).body(u);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/usuarios/modificar/{id}")
	public ResponseEntity<Usuario> modificarUsuario(@RequestBody UsuarioDTO udto, @PathVariable Integer id)
			throws URISyntaxException {
		Usuario us = udao.getUsuario(id);
		if (us.getUsuario().equals(udto.getUsuario())
				||(!udto.getUsuario().isEmpty() && udao.getUsuario(udto.getUsuario()) == null)) {
			Encriptar e = new Encriptar();
			String salto = e.getSalto(10);
			String h = e.getHash(udto.getPassword(), salto);
			Usuario u = udao.getUsuario(id);
			u.setUsuario(udto.getUsuario());
			u.setHash(h);
			u.setSalto(salto);
			u.setDireccion(udto.getDireccion());
			u.setNombre(udto.getNombre());
			u.setApellido(udto.getApellido());
			u.setTelefono(udto.getTelefono());
			u.setEmail(udto.getEmail());
			u.setAdmin(false);
			u = udao.añadirUsuario(u);
			return ResponseEntity.created(new URI("http://localhost:8082/usuarios/" + u.getIdUsuario())).body(u);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/usuarios/eliminar/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
		udao.eliminarUsuario(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/reservas")
	public List<Reserva> getReservas() {
		return rdao.getReservas();
	}

	@GetMapping("/reservas/usuarios/{idUsuario}")
	public ReservaDTO getReservasFecha(@PathVariable Integer idUsuario,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fInicial,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fFinal,
			@RequestParam(defaultValue = "1") Integer p, @RequestParam(defaultValue = "2") Integer pp) {
		if (fFinal == null) {
			fFinal = fInicial;
		}

		return rdao.getReservasFecha(fInicial, fFinal, idUsuario, p, pp);
	}

	@GetMapping("/reservas/{id}")
	public ResponseEntity<Reserva> getReserva(@PathVariable Integer id) {
		Reserva r = rdao.getReserva(id);
		if (r == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(r);
		}
	}

	@GetMapping("/viajes/{id}/reservas")
	public List<Reserva> getReservaViaje(@PathVariable Integer id) {
		return rdao.getReservaViaje(id);
	}

	@PostMapping("/viajes/{id}/reservas")
	public ResponseEntity<Reserva> añadirReserva(@RequestBody Reserva r, @PathVariable Integer id) {
		Viaje v = vdao.getViaje(id);
		if (v == null) {
			return ResponseEntity.notFound().build();
		} else {
			r.setViaje(v);
			rdao.añadirReserva(r);
			return ResponseEntity.ok(r);
		}

	}

	@DeleteMapping("/reservas/eliminar/{id}")
	public ResponseEntity<?> eliminarReserva(@PathVariable Integer id) {
		rdao.eliminarReserva(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("viajes/{idViaje}/reservas/modificar/{idReserva}")
	public ResponseEntity<Reserva> modificarReserva(@RequestBody Reserva r, @PathVariable Integer idReserva,
			@PathVariable Integer idViaje) {
		Viaje v = vdao.getViaje(idViaje);
		Reserva res = rdao.getReserva(idReserva);
		if (v == null || res == null) {
			return ResponseEntity.notFound().build();
		} else {
			if (res.getViaje().getIdViaje() != idViaje) {
				return ResponseEntity.notFound().build();
			} else {
				r.setIdReserva(idReserva);
				r.setViaje(v);
				rdao.añadirReserva(r);
				return ResponseEntity.ok(r);
			}
		}

	}

	@GetMapping("/viajes")
	public List<Viaje> getViajes() {
		return vdao.getViajes();
	}

	@GetMapping("/usuarios/{id}/viajes")
	public ViajeDTO getViajeUsuario(@PathVariable Integer id, @RequestParam(defaultValue = "1") Integer p,
			@RequestParam(defaultValue = "2") Integer pp) {
		return vdao.getViajesUsuario(id, p, pp);
	}

	@GetMapping("/viajes/{id}")
	public ResponseEntity<Viaje> getViaje(@PathVariable Integer id) {
		Viaje v = vdao.getViaje(id);
		if (v == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(v);
		}
	}

	@PostMapping("/usuarios/{id}/viajes")
	public ResponseEntity<Viaje> añadirViaje(@RequestBody Viaje v, @PathVariable Integer id) {
		Usuario u = udao.getUsuario(id);
		if (u == null) {
			return ResponseEntity.notFound().build();
		} else {
			v.setUsuario(u);
			vdao.añadirViaje(v);
			return ResponseEntity.ok(v);
		}
	}

	@DeleteMapping("/viajes/eliminar/{id}")
	public ResponseEntity<?> eliminarViaje(@PathVariable Integer id) {
		List<Reserva> reservas=rdao.getReservaViaje(id);
		for(Reserva r:reservas) {
			rdao.eliminarReserva(r.getIdReserva());
		}
		vdao.eliminarViaje(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/usuarios/{idUsuario}/viajes/modificar/{id}")
	public ResponseEntity<Viaje> modificarViaje(@RequestBody Viaje v, @PathVariable Integer id,
			@PathVariable Integer idUsuario) {
		Usuario u = udao.getUsuario(idUsuario);
		Viaje vi = vdao.getViaje(id);
		if (u == null || vi == null) {
			return ResponseEntity.notFound().build();
		} else {
			if (vi.getUsuario().getIdUsuario() != idUsuario) {
				return ResponseEntity.notFound().build();
			} else {
				v.setUsuario(u);
				v.setIdViaje(id);
				vdao.añadirViaje(vi);
				return ResponseEntity.ok(v);
			}
		}
	}
}
