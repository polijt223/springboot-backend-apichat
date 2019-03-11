package com.tulian.springboot.apirest.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tulian.springboot.apirest.app.models.entity.Cliente;
import com.tulian.springboot.apirest.app.models.entity.Region;
import com.tulian.springboot.apirest.app.models.services.IClienteService;
import com.tulian.springboot.apirest.app.models.services.IRegionService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IRegionService regionService;
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	
	@GetMapping("/clientes")
	public List<Cliente> index (){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index (@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 5);
		return clienteService.findAll(pageable);
	}
	
	
	   // Se utiliza ResponseEntity para que el tipo de respuesta que se de al front pueda variar es decir puede ser un error 
									//, el cliente o un conjunto de errores, esta respuesta va a ser casteada a un Response
	@GetMapping("/clientes/{id}") 	//Como no sabemos que tipo de respuesta pueda haber el Casteo a ResponseEntity se va a aplicar a "?" para que resirva cualquier Objeto , "?" Seria una forma de definir que se utilizara algo generico
	public ResponseEntity<?> show(@PathVariable("id") Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException dae) {
			response.put("mensaje", "Cliente Id :"+id.toString()+"n/"+dae.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("mensaje", "Cliente Id :".concat(id.toString().concat(" No existe en la base")));
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)     
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {    //Se anota con RequestBody porque resivimos el objeto en formato JSon desde el FrontEnd
		  
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
		
		//Metodo anterior al jdk8
		/*	List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo' "+err.getField()+"' "+err.getDefaultMessage());
			}
		*/	
			
		//Metodo Actuallizado	
		    response.put("errors", this.obtenerErrores(result));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		  
		}
		
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException dae) {
			response.put("mensaje", "Error al crear nuevo cliente");
			response.put("error" , dae.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		  
		  response.put("mensaje", "El cliente fue creado con éxito!");
		  response.put("cliente", clienteNew);
		  return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")   // Se utiliza put para actulizar datos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable ("id") Long id) {
		Cliente clienteActual = null;
		Map<String, Object> response = new HashMap<>();
		Cliente clienteNew = null;
		
		if(result.hasErrors()) {
		    response.put("errors", this.obtenerErrores(result));
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		  }
		
		clienteActual=clienteService.findById(id);
		if (clienteActual== null) {
			response.put("mensaje", "No se pudo editar el cliente ");
			response.put("error", "el cliente con id "+id+" no exite en la base de datos");
			return new ResponseEntity<Map<String,Object>> (response,HttpStatus.NOT_FOUND);
		}
		
		
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setEmail(cliente.getEmail());
		clienteActual.setCreateAt(cliente.getCreateAt());
		clienteActual.setRegion(cliente.getRegion());
		
		try {
			clienteNew = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "Se actualizo con exito el cliente: "+id);
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();
			clienteService.delete(id);
			
			if (nombreFotoAnterior!=null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro de la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		response.put("mensaje", "Se elimino con exito el registro: "+id);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());  //spring core io es el import
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();
			
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id ){
		
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
		if(!archivo.isEmpty()) {
			
			// la clase UUID devuelve  una cadena de caracteres ramdom a fin de poder utilizar como un id unico
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir imagen "+nombreArchivo);
				response.put("error", e.getCause().getMessage());
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			
			if (nombreFotoAnterior!=null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			cliente.setFoto(nombreArchivo);
			
			clienteService.save(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido con correctamente la imagen: "+nombreArchivo);
		}
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	private List<String> obtenerErrores(BindingResult result) {
			
	    List<String> errors = result.getFieldErrors()
	        .stream()
	        .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
	        .collect(Collectors.toList());
	    return errors;				
	  }
		
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones (){
		return regionService.findAll();
	}
	
	
	
	
	
	
	
}
