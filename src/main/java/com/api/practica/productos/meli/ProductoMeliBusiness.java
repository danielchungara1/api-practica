package com.api.practica.productos.meli;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.exceptions.CustomException;
import com.api.practica.exceptions.ResourceNotFoundException;
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

	public List<ProductoMeliDto> getProductosPorNombre(String nombre, Integer limite, Integer offset) throws Exception {
		return this.sendGet(nombre, limite, offset);
	}

	private List<ProductoMeliDto> sendGet(String txt, Integer limite, Integer offset) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String endPointProductos = String.format("https://api.mercadolibre.com/sites/MLA/search?q=%s&offset=%d&limit=%d", txt, offset, limite);
		HttpGet requestProductos = new HttpGet(endPointProductos);

		try (CloseableHttpResponse responseGetProductos = httpClient.execute(requestProductos)) {
			if (responseGetProductos.getStatusLine().getStatusCode() == 200){
				HttpEntity entityProductos = responseGetProductos.getEntity();
				if (entityProductos != null) {
					// return it as a String
					String responseBodyProductos = EntityUtils.toString(entityProductos);

					// Org.Json
					JSONObject objProductos = new JSONObject(responseBodyProductos);
					JSONArray resultsProductos = objProductos.getJSONArray("results");

					// Jackson
					ObjectMapper mapperProductos = new ObjectMapper();
					List<ProductoMeliDto> productos = Arrays.asList(mapperProductos.readValue(resultsProductos.toString(), ProductoMeliDto[].class));

					// Agregar Pictures
					productos.forEach(productoMeliDto -> {
						String itItem = productoMeliDto.getId();
						String endPointItem = "https://api.mercadolibre.com/items/" + itItem;
						HttpGet requestItem = new HttpGet(endPointItem);
						try (CloseableHttpResponse responseGetItem = httpClient.execute(requestItem)) {
							if (responseGetItem.getStatusLine().getStatusCode() == 200){
								HttpEntity entityItem = responseGetItem.getEntity();
								if (entityItem != null) {
									// return it as a String
									String responseBodyItem = EntityUtils.toString(entityItem);

									// Org.Json
									JSONObject objItem = new JSONObject(responseBodyItem);
									JSONArray resultPictures = objItem.getJSONArray("pictures");

									// Jackson
									ObjectMapper mapperPictures = new ObjectMapper();
									List<PictureItemDto> pictures = Arrays.asList(mapperProductos.readValue(resultPictures.toString(), PictureItemDto[].class));

									// Agrego las pictures
									productoMeliDto.setPictures(pictures);
								}
							}
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					});
					return productos;
				}
			}
			throw new CustomException("Error al buscar productos de mercado libre.", HttpStatus.valueOf(responseGetProductos.getStatusLine().getStatusCode()));
		}
	}
}
