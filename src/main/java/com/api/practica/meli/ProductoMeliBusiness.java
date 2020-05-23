package com.api.practica.meli;

import com.api.practica.dtos.CollectionPaginatedDto;
import com.api.practica.productos.Producto_;
import com.api.practica.services.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoMeliBusiness {

	@Autowired
	BackendService backendService;

	public CollectionPaginatedDto<ProductoMeliDto> getProductosPorNombre(String nombre, Integer limite, Integer offset) throws Exception {

		return this.sendGet(nombre, limite, offset);
	}

	private CollectionPaginatedDto<ProductoMeliDto> sendGet(String txt, Integer limite, Integer offset) throws Exception {

//		//Busco los productos
//		txt = txt.replace(" ", "%20");
//		String endPointProductos = String.format("https://api.mercadolibre.com/sites/MLA/search?q=%s&offset=%d&limit=%d", txt, offset, limite);
//
//		CollectionPaginatedDto<ProductoMeliDto> productosPaginados =
//				this.backendService.getCollectionPaginatedDto(endPointProductos, ProductoMeliDto.class);
//
//		//Agrego las imagenes
//		for (ProductoMeliDto productoMeliDto : productosPaginados.getResults()) {
//			String endPointItem = "https://api.mercadolibre.com/items/" + productoMeliDto.getId();
//			List<PictureItemDto> pictures = this.backendService.getArray(endPointItem, "pictures", PictureItemDto[].class);
//			productoMeliDto.setPictures(pictures);
//		}
//
//		return productosPaginados;
		return null;
	}
}
