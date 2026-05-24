package com.ecommerce.catalgo.infrastructure.entry_points;

import com.ecommerce.catalgo.domain.model.Producto;
import com.ecommerce.catalgo.domain.usecase.ProductoUseCase;
import com.ecommerce.catalgo.infrastructure.driver_adapters.jpa_repository.ProductoData;
import com.ecommerce.catalgo.infrastructure.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ecommerce/producto")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoUseCase productoUseCase;
    private final ProductoMapper productoMapper;

    @PostMapping("/save")
    public ResponseEntity<Producto> saveProducto(@RequestBody ProductoData productoData){

        Producto productoGuardado = productoUseCase.guardarProducto(productoMapper.topruducto(productoData));

        return ResponseEntity.ok(productoGuardado);
    }

    @GetMapping("/buscar/{productoId}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable String productoId){

        Producto productoEncontrado = productoUseCase.buscarProductoPorId(productoId);

        return ResponseEntity.ok(productoEncontrado);
    }

    @DeleteMapping("/eliminar/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String productoId){

        productoUseCase.eliminarProductoPorId(productoId);

        return ResponseEntity.noContent().build();
    }
}

