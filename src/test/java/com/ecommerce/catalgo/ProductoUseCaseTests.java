package com.ecommerce.catalgo;


import com.ecommerce.catalgo.domain.model.Producto;
import com.ecommerce.catalgo.domain.model.gateway.ProductoGateway;
import com.ecommerce.catalgo.domain.usecase.ProductoUseCase;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoUseCaseTest {

    @Mock
    private ProductoGateway productoGateway;

    @InjectMocks
    private ProductoUseCase productoUseCase;

    @Test
    void guardarProducto() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Mouse Gamer");
        producto.setPrecio(120000.0);
        producto.setStock(10);

        when(productoGateway.guardarProducto(any(Producto.class)))
                .thenReturn(producto);

        // Act
        Producto resultado = productoUseCase.guardarProducto(producto);

        // Assert
        assertEquals("P001", resultado.getProductoId());
        assertEquals("Mouse Gamer", resultado.getNombre());
        assertEquals(120000.0, resultado.getPrecio());
        assertEquals(10, resultado.getStock());

        verify(productoGateway)
                .guardarProducto(any(Producto.class));
    }

    @Test
    void guardarProductoCuandoStockEsNull() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Teclado");
        producto.setPrecio(50000.0);
        producto.setStock(null);

        when(productoGateway.guardarProducto(any(Producto.class)))
                .thenReturn(producto);

        // Act
        Producto resultado = productoUseCase.guardarProducto(producto);

        // Assert
        assertEquals(0, resultado.getStock());

        verify(productoGateway)
                .guardarProducto(any(Producto.class));
    }

    @Test
    void excepcionCuandoIdEsNull() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId(null);
        producto.setNombre("Mouse");
        producto.setPrecio(100000.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "Id obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoIdEstaVacio() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("");
        producto.setNombre("Mouse");
        producto.setPrecio(100000.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "Id obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoNombreEsNull() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre(null);
        producto.setPrecio(100000.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "Nombre obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoNombreEstaVacio() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("");
        producto.setPrecio(100000.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "Nombre obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoPrecioEsNull() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Monitor");
        producto.setPrecio(null);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "El precio no puede ser null",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoPrecioEsNegativo() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Monitor");
        producto.setPrecio(-1000.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "El precio debe ser positivo",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoPrecioEsCero() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Monitor");
        producto.setPrecio(0.0);
        producto.setStock(5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "El precio debe ser positivo",
                exception.getMessage()
        );
    }

    @Test
    void excepcionCuandoStockEsNegativo() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Monitor");
        producto.setPrecio(100000.0);
        producto.setStock(-5);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoUseCase.guardarProducto(producto)
        );

        // Assert
        assertEquals(
                "No puede tener stock negativo",
                exception.getMessage()
        );
    }

    @Test
    void buscarProductoPorId() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Laptop");
        producto.setPrecio(2500000.0);
        producto.setStock(3);

        when(productoGateway.buscarProductoPorId("P001"))
                .thenReturn(producto);

        // Act
        Producto resultado = productoUseCase.buscarProductoPorId("P001");

        // Assert
        assertEquals("P001", resultado.getProductoId());
        assertEquals("Laptop", resultado.getNombre());

        verify(productoGateway)
                .buscarProductoPorId("P001");
    }

    @Test
    void buscarProductoPorIdExcepcionCuandoNoExiste() {
        // Arrange
        when(productoGateway.buscarProductoPorId("P001"))
                .thenReturn(null);

        // Act
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> productoUseCase.buscarProductoPorId("P001")
        );

        // Assert
        assertEquals(
                "Producto no encontrado",
                exception.getMessage()
        );

        verify(productoGateway)
                .buscarProductoPorId("P001");
    }

    @Test
    void eliminarProductoPorId() {
        // Arrange
        Producto producto = new Producto();

        producto.setProductoId("P001");
        producto.setNombre("Laptop");

        when(productoGateway.buscarProductoPorId("P001"))
                .thenReturn(producto);

        // Act
        productoUseCase.eliminarProductoPorId("P001");

        // Assert
        verify(productoGateway)
                .buscarProductoPorId("P001");

        verify(productoGateway)
                .eliminarProductoPorId("P001");
    }

    @Test
    void eliminarProductoPorIdExcepcionCuandoNoExiste() {
        // Arrange
        when(productoGateway.buscarProductoPorId("P001"))
                .thenReturn(null);

        // Act
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> productoUseCase.eliminarProductoPorId("P001")
        );

        // Assert
        assertEquals(
                "Producto no encontrado",
                exception.getMessage()
        );

        verify(productoGateway)
                .buscarProductoPorId("P001");
    }
}