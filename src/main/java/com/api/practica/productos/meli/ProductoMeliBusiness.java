package com.api.practica.productos.meli;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.dtos.CollectionPaginatedDto;
import com.api.practica.exceptions.CustomException;
import com.api.practica.exceptions.ResourceNotFoundException;
import com.api.practica.services.BackendService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoMeliBusiness {

	@Autowired
	BackendService backendService;

	public CollectionPaginatedDto<ProductoMeliDto> getProductosPorNombre(String nombre, Integer limite, Integer offset) throws Exception {
		return this.sendGet(nombre, limite, offset);
	}

	private CollectionPaginatedDto<ProductoMeliDto> sendGet(String txt, Integer limite, Integer offset) throws Exception {

		String endPointProductos = String.format("https://api.mercadolibre.com/sites/MLA/search?q=%s&offset=%d&limit=%d", txt, offset, limite);
		JSONObject jsonProductos =  this.backendService.get(endPointProductos);
		JSONArray resultsProductos = jsonProductos.getJSONArray("results");

		ObjectMapper mapperProductos = new ObjectMapper();
		List<ProductoMeliDto> productos = Arrays.asList(mapperProductos.readValue(resultsProductos.toString(), ProductoMeliDto[].class));
		CollectionPaginatedDto<ProductoMeliDto> productosPaginados = new CollectionPaginatedDto<>();
		productosPaginados.setResultados(productos);
		productosPaginados.setLimit(jsonProductos.getJSONObject("paging").getLong("limit"));
		productosPaginados.setOffset(jsonProductos.getJSONObject("paging").getLong("offset"));
		productosPaginados.setTotal(jsonProductos.getJSONObject("paging").getLong("total"));

		//Agrego las imagenes
		for (ProductoMeliDto productoMeliDto : productos) {
			String endPointItem = "https://api.mercadolibre.com/items/" + productoMeliDto.getId();
			JSONObject jsonPictures = this.backendService.get(endPointItem);
			JSONArray resultPictures = jsonPictures.getJSONArray("pictures");

			// Jackson
			ObjectMapper mapperPictures = new ObjectMapper();
			List<PictureItemDto> pictures = Arrays.asList(mapperProductos.readValue(resultPictures.toString(), PictureItemDto[].class));

			// Agrego las pictures
			productoMeliDto.setPictures(pictures);
		}

		return productosPaginados;
	}
}
