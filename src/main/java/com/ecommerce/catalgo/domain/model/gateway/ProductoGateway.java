package com.ecommerce.catalgo.domain.model.gateway;

import com.ecommerce.catalgo.domain.model.Producto;

public interface ProductoGateway {
    Producto guardarProducto(Producto producto);

    Producto buscarProductoPorId (String productoId);

    void eliminarProductoPorId (String productoId);
}
