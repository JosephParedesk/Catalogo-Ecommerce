package com.ecommerce.catalgo.domain.usecase;

import com.ecommerce.catalgo.domain.model.Producto;
import com.ecommerce.catalgo.domain.model.gateway.ProductoGateway;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class ProductoUseCase {

    private final ProductoGateway productoGateway;

    public Producto guardarProducto(Producto producto){

        if(producto.getProductoId() == null || producto.getProductoId().isBlank()){
            throw new RuntimeException("Id obligatorio");
        }

        if(producto.getNombre() == null || producto.getNombre().isBlank()){
            throw new RuntimeException("Nombre obligatorio");
        }

        if(producto.getStock() == null){
            producto.setStock(0);
        }

        if(producto.getPrecio() == null){
            throw new RuntimeException("El precio no puede ser null");
        }

        if(producto.getPrecio() <= 0){
            throw new RuntimeException("El precio debe ser positivo");
        }

        if(producto.getStock() < 0){
            throw new RuntimeException("No puede tener stock negativo");
        }

        return productoGateway.guardarProducto(producto);
    }

    public Producto buscarProductoPorId(String productoId){

        Producto producto = productoGateway.buscarProductoPorId(productoId);

        if(producto == null){
            throw new NoSuchElementException("Producto no encontrado");
        }

        return producto;
    }

    public void eliminarProductoPorId(String productoId){

        Producto producto = productoGateway.buscarProductoPorId(productoId);

        if(producto == null){
            throw new NoSuchElementException("Producto no encontrado");
        }

        productoGateway.eliminarProductoPorId(productoId);
    }
}