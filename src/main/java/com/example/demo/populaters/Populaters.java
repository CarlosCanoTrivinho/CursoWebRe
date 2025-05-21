package com.example.demo.populaters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.demo.Entities.Alumno;
import com.example.demo.Entities.AlumnoEdicion;
import com.example.demo.Entities.Curso;
import com.example.demo.Entities.Edicion;
import com.example.demo.Entities.ElementoEvaluado;
import com.example.demo.Entities.Enunciado;
import com.example.demo.Entities.Respuesta;
import com.example.demo.Entities.Tarea;
import com.example.demo.Entities.Tutor;
import com.example.demo.POJO.Categoria;
import com.example.demo.POJO.Situacion;
import com.example.demo.Repositories.AlumnoEdicionRepository;
import com.example.demo.Repositories.AlumnoRepository;
import com.example.demo.Repositories.CursoRepository;
import com.example.demo.Repositories.EdicionRepository;
import com.example.demo.Repositories.ElementoEvaluadoRepository;
import com.example.demo.Repositories.EnunciadoRepository;
import com.example.demo.Repositories.RespuestaRepository;
import com.example.demo.Repositories.TareaRepository;
import com.example.demo.Repositories.TutorRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class Populaters {

	private final AlumnoRepository alumnoRepository;
	private final TutorRepository tutorRepository;
	private final CursoRepository cursoRepository;
	private final EdicionRepository edicionRepository;
	private final AlumnoEdicionRepository alumnoEdicionRepository;
	private final TareaRepository tareaRepository;
	private final ElementoEvaluadoRepository elementoEvaluadoRepository;
	private final RespuestaRepository respuestaRepository;
	private final EnunciadoRepository enunciadoRepository;

	public Populaters(AlumnoRepository alumnoRepository, TutorRepository tutorRepository,
			CursoRepository cursoRepository, EdicionRepository edicionRepository,
			AlumnoEdicionRepository alumnoEdicionRepository, TareaRepository tareaRepository,
			ElementoEvaluadoRepository elementoEvaluadoRepository, RespuestaRepository respuestaRepository,
			EnunciadoRepository enunciadoRepository) {
		this.alumnoRepository = alumnoRepository;
		this.tutorRepository = tutorRepository;
		this.cursoRepository = cursoRepository;
		this.edicionRepository = edicionRepository;
		this.alumnoEdicionRepository = alumnoEdicionRepository;
		this.tareaRepository = tareaRepository;
		this.elementoEvaluadoRepository = elementoEvaluadoRepository;
		this.respuestaRepository = respuestaRepository;
		this.enunciadoRepository = enunciadoRepository;
	}

	@Transactional
	@PostConstruct
	public void populate() {
		// 1. Crear 10 Alumnos
		List<Alumno> alumnos = crearAlumnos();
		alumnos = alumnoRepository.saveAll(alumnos);

		// 2. Crear 10 Tutores
		List<Tutor> tutores = crearTutores();
		tutorRepository.saveAll(tutores);

		// 3. Crear 10 Cursos
		List<Curso> cursos = crearCursos();
		cursos = cursoRepository.saveAll(cursos);

		// 4. Crear 20 Ediciones (2 por curso)
		List<Edicion> ediciones = crearEdiciones(cursos, tutores);
		ediciones = edicionRepository.saveAll(ediciones);

		// 5. Matricular alumnos en ediciones
		List<AlumnoEdicion> matriculas = crearMatriculas(alumnos, ediciones);
		alumnoEdicionRepository.saveAll(matriculas);
		desmatricular();
		// 6. Crear 10 Tareas (1 por curso)
		List<Tarea> tareas = crearTareas(cursos);
		tareaRepository.saveAll(tareas);

		// 7. Crear Elementos Evaluados
		List<ElementoEvaluado> elementos = crearElementosEvaluados(matriculas, tareas);
		elementoEvaluadoRepository.saveAll(elementos);

		// 8. Crear Enunciados
		List<Enunciado> enunciados = crearEnunciados(tareas);
		enunciadoRepository.saveAll(enunciados);

		// 9. Crear Respuestas
		List<Respuesta> respuestas = crearRespuestas(elementos, enunciados);
		respuestaRepository.saveAll(respuestas);

		
	}

	private List<Alumno> crearAlumnos() {
		List<Alumno> alumnos = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Alumno alumno = new Alumno();
			alumno.setNombre("Alumno " + i);
			alumno.setApellidos("Apellido " + i);
			alumno.setCorreo("alumno" + i + "@example.com");
			alumno.setFechaNac(LocalDate.of(2000, 1, 1).minusYears(i));
			alumnos.add(alumno);
		}
		return alumnos;
	}

	private List<Tutor> crearTutores() {
		List<Tutor> tutores = new ArrayList<>();
		Random random = new Random();
		for (int i = 1; i <= 10; i++) {
			Tutor tutor = new Tutor();
			tutor.setNombre("Tutor " + i);
			tutor.setApellidos("Apellido " + i);
			tutor.setCorreo("tutor" + i + "@example.com");
			tutor.setCategoria(Categoria.values()[random.nextInt(Categoria.values().length)]);
			tutores.add(tutor);
		}
		return tutores;
	}

	private List<Curso> crearCursos() {
		List<Curso> cursos = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Curso curso = new Curso();
			curso.setTitulo("Curso " + i);
			curso.setPrecio(100.0 * i);
			curso.setCategoria(Categoria.values()[i % Categoria.values().length]);
			cursos.add(curso);
		}
		return cursos;
	}

	private List<Edicion> crearEdiciones(List<Curso> cursos, List<Tutor> tutores) {
		List<Edicion> ediciones = new ArrayList<>();
		Random random = new Random();
		for (Curso curso : cursos) {
			for (int j = 1; j <= 2; j++) {
				Edicion edicion = new Edicion();
				edicion.setNumEdicion((short) j);
				edicion.setPlazas((short) j);
				edicion.setFechaInicio(LocalDate.now().plusMonths(j));
				edicion.setFechaFinalizacion(edicion.getFechaInicio().plusMonths(3));
				edicion.setCurso(curso);
				edicion.setTutor(tutores.get(random.nextInt(tutores.size())));
				ediciones.add(edicion);
			}
		}

		Edicion edicionAbandonoSinCubrirPlazas = new Edicion();
		edicionAbandonoSinCubrirPlazas.setNumEdicion((short) 3);
		edicionAbandonoSinCubrirPlazas.setPlazas((short) 10);
		edicionAbandonoSinCubrirPlazas.setFechaInicio(LocalDate.now().plusMonths(2));
		edicionAbandonoSinCubrirPlazas
				.setFechaFinalizacion(edicionAbandonoSinCubrirPlazas.getFechaInicio().plusMonths(4));
		edicionAbandonoSinCubrirPlazas.setCurso(buscarCursoPorId(1, cursos));
		edicionAbandonoSinCubrirPlazas.setTutor(tutores.get(random.nextInt(tutores.size())));
		ediciones.add(edicionAbandonoSinCubrirPlazas);

		Edicion edicionSinAbandonoCubriendoPlazas = new Edicion();
		edicionSinAbandonoCubriendoPlazas.setNumEdicion((short) 4);
		edicionSinAbandonoCubriendoPlazas.setPlazas((short) 10);
		edicionSinAbandonoCubriendoPlazas.setFechaInicio(LocalDate.now().plusMonths(2));
		edicionSinAbandonoCubriendoPlazas
				.setFechaFinalizacion(edicionSinAbandonoCubriendoPlazas.getFechaInicio().plusMonths(4));
		edicionSinAbandonoCubriendoPlazas.setCurso(buscarCursoPorId(1, cursos));
		edicionSinAbandonoCubriendoPlazas.setTutor(tutores.get(random.nextInt(tutores.size())));
		ediciones.add(edicionSinAbandonoCubriendoPlazas);

		Edicion edicionSinAbandonoSinCubrirPlazas = new Edicion();
		edicionSinAbandonoSinCubrirPlazas.setNumEdicion((short) 5);
		edicionSinAbandonoSinCubrirPlazas.setPlazas((short) 10);
		edicionSinAbandonoSinCubrirPlazas.setFechaInicio(LocalDate.now().plusMonths(2));
		edicionSinAbandonoSinCubrirPlazas
				.setFechaFinalizacion(edicionSinAbandonoSinCubrirPlazas.getFechaInicio().plusMonths(4));
		edicionSinAbandonoSinCubrirPlazas.setCurso(buscarCursoPorId(1, cursos));
		edicionSinAbandonoSinCubrirPlazas.setTutor(tutores.get(random.nextInt(tutores.size())));
		ediciones.add(edicionSinAbandonoSinCubrirPlazas);

		Edicion edicionAbandonoCubriendoPlazas = new Edicion();
		edicionAbandonoCubriendoPlazas.setNumEdicion((short) 6);
		edicionAbandonoCubriendoPlazas.setPlazas((short) 10);
		edicionAbandonoCubriendoPlazas.setFechaInicio(LocalDate.now().plusMonths(2));
		edicionAbandonoCubriendoPlazas
				.setFechaFinalizacion(edicionAbandonoCubriendoPlazas.getFechaInicio().plusMonths(4));
		edicionAbandonoCubriendoPlazas.setCurso(buscarCursoPorId(1, cursos));
		edicionAbandonoCubriendoPlazas.setTutor(tutores.get(random.nextInt(tutores.size())));
		ediciones.add(edicionAbandonoCubriendoPlazas);
		return ediciones;
	}

	public List<AlumnoEdicion> crearMatriculas(List<Alumno> alumnos, List<Edicion> ediciones) {
		List<AlumnoEdicion> matriculas = new ArrayList<>();
		Random random = new Random();

		for (Alumno alumno : alumnos) {
			// Para cada alumno, matricularlo en hasta 5 ediciones distintas
			Set<Edicion> edicionesAsignadas = new HashSet<>();

			while (edicionesAsignadas.size() < 5) {
				Edicion edicion = ediciones.get(random.nextInt(20));

				// Evitar matricular al mismo alumno varias veces en la misma edición
				if (!edicionesAsignadas.contains(edicion)) {
					edicionesAsignadas.add(edicion);

					AlumnoEdicion matricula = new AlumnoEdicion();
					matricula.setAlumno(alumno);
					matricula.setEdicion(edicion);

					// Asignar aleatoriamente Matriculado o Desmatriculado
					Situacion situacion = random.nextBoolean() ? Situacion.Matriculado : Situacion.Desmatriculado;
					matricula.setSituacion(situacion);

					matriculas.add(matricula);
				}
			}
		}
		for (int k = 3; k <= 6; k++) {
			for (int i = 0; i < 10; i++) {
				if (k == 3 && i == 4 || k == 5 && i == 4) {
					break;
				}
				AlumnoEdicion alumnoEdicion = new AlumnoEdicion();
				alumnoEdicion.setAlumno(alumnos.get(i));
				alumnoEdicion.setEdicion(buscarPorNumEdicion(1, (short) k, ediciones));
				Situacion situacion = Situacion.Matriculado;
				alumnoEdicion.setSituacion(situacion);
				matriculas.add(alumnoEdicion);
				
			}
		}

		return matriculas;
	}

	private Edicion buscarPorNumEdicion(long idCurso, short numEdicion, List<Edicion> ediciones) {
		return ediciones.stream()
				.filter(edicion -> edicion.getCurso().getId().equals(idCurso) && edicion.getNumEdicion() == numEdicion)
				.findFirst().get();
	}

	private Curso buscarCursoPorId(long id, List<Curso> cursos) {

		return cursos.stream().filter(curso -> curso.getId() == id).findFirst().get();
	}

	private void desmatricular() {
		List<AlumnoEdicion> byEdicion = alumnoEdicionRepository.findByEdicion(edicionRepository.findById(21L).get());
		for (int i = 0; i < 4; i++) {
			byEdicion.get(i).setSituacion(Situacion.Desmatriculado);
		}
		
		alumnoEdicionRepository.saveAll(byEdicion);
		List<AlumnoEdicion> byEdicion2 = alumnoEdicionRepository.findByEdicion(edicionRepository.findById(24L).get());
		for (int i = 0; i < 8; i++) {
			byEdicion2.get(i).setSituacion(Situacion.Desmatriculado);
		}
		
		alumnoEdicionRepository.saveAll(byEdicion2);

	}

	private List<Tarea> crearTareas(List<Curso> cursos) {
		List<Tarea> tareas = new ArrayList<>();
		for (Curso curso : cursos) {
			Tarea tarea = new Tarea();
			tarea.setDescripcion("Tarea del curso " + curso.getTitulo());
			tareas.add(tarea);
		}
		return tareas;
	}

	private List<ElementoEvaluado> crearElementosEvaluados(List<AlumnoEdicion> matriculas, List<Tarea> tareas) {
		List<ElementoEvaluado> elementos = new ArrayList<>();
		Random random = new Random();
		for (AlumnoEdicion matricula : matriculas) {
			for (int i = 0; i < 2; i++) {
				ElementoEvaluado elemento = new ElementoEvaluado();
				elemento.setAlumnoEdicion(matricula);
				elemento.setTarea(tareas.get(random.nextInt(tareas.size())));
				elemento.setNota(5.0 + random.nextDouble() * 5);
				elementos.add(elemento);
			}
		}
		return elementos;
	}

	private List<Enunciado> crearEnunciados(List<Tarea> tareas) {
		List<Enunciado> enunciados = new ArrayList<>();
		for (Tarea tarea : tareas) {
			for (int i = 1; i <= 3; i++) {
				Enunciado enunciado = new Enunciado();
				enunciado.setTexto("Enunciado " + i + " de " + tarea.getDescripcion());
				enunciado.setTarea(tarea); // Relación importante añadida
				enunciados.add(enunciado);
			}
		}
		return enunciados;
	}

	private List<Respuesta> crearRespuestas(List<ElementoEvaluado> elementos, List<Enunciado> enunciados) {
		List<Respuesta> respuestas = new ArrayList<>();
		Random random = new Random();
		for (ElementoEvaluado elemento : elementos) {
			// Solo crear 1 respuesta por elemento y enunciado relacionado
			Enunciado enunciado = enunciados.get(random.nextInt(enunciados.size()));
			Respuesta respuesta = new Respuesta();
			respuesta.setTexto("Respuesta de " + elemento.getAlumnoEdicion().getAlumno().getNombre());
			respuesta.setEnunciado(enunciado);
			respuesta.setElementoEvaluado(elemento);
			respuestas.add(respuesta);
		}
		return respuestas;
	}

	public List<Edicion> getEdiciones() {
		return edicionRepository.findAll();
	}

}
