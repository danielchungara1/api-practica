package com.api.practica.productos.meli;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoMeliBusiness {

	public Mono<String> getProductosPorNombre(String nombre, Integer limite, Integer offset) {
		String endPoint = "https://api.mercadolibre.com/sites/MLA/search?q=${nombre}&offset=${offset}&limit=${limite}";
		String hardEndPoint = "https://api.mercadolibre.com/sites/MLA/search?q=ipod%20nano&offset=0&limit=1";

		WebClient webClient = WebClient.create(hardEndPoint);
		Mono<String> result = webClient.get()
				.retrieve()
				.bodyToMono(String.class);
		result.subscribe();
		return result;
	}

}
