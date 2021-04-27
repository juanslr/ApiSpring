package com.init.allforone.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.allforone.dao.ProductsDAO;
import com.init.allforone.entitys.Product;

@RestController //con esta especificamos que esta clase es un servicio rest
@RequestMapping("products")// la url de los servicios/metodos de esta clase

public class ProductRest {
	//aqui van todos los servicios rest de la API
	
	@Autowired //gracias a esto nuestra variable tendra un objeto valido
	private ProductsDAO productDAO; 
	
	//consultar todos los productos
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> productos = productDAO.findAll(); //sentencia sql
		return ResponseEntity.ok(productos);
	}
	
	//consultar por medio de ID
	@RequestMapping(value="{productId}") //sirve para personalizar la url (localhost:8080/products/{productId})	
	public ResponseEntity<Product> getProductById(@PathVariable("productId")Long productId){ //mediante pathvariable asignamos id a value de request mapping
		Optional<Product> optionalProduct = productDAO.findById(productId); //sentencia sql - (gracias a option evitamos valores null)
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();

		}
	}
	
	/*  --- INSERTAR VALORES ---*/
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){ //lo que venga en la peticion lo parseamos a tipo product
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	/*  --- ELIMINAR VALORES ---*/
	@DeleteMapping(value="{productId}") //(localhost:8080/products/{productId})	Esto se envia con peticion delete mediante postman en mi caso
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId")Long productId){ 
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	/*  --- ACTUALIZAR VALORES ---*/
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productDAO.findById(product.getId()); 
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct); //regresamos el nuevo producto actualizado
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	/* 
	 ---- EJEMPLO BASICO -----
	 
	@GetMapping //decimos que este servicio esta disponible mediante el metodo get (localhost:8080)
	@RequestMapping(value="test", method=RequestMethod.GET) //sirve para personalizar la url (localhost:8080/test)	
	 
	public String demo() {
		return "Hola";
	}
 */
	
}
