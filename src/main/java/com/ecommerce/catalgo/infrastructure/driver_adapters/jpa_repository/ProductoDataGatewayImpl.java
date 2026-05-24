package com.ecommerce.catalgo.infrastructure.driver_adapters.jpa_repository;


import com.ecommerce.catalgo.domain.model.Producto;
import com.ecommerce.catalgo.domain.model.gateway.ProductoGateway;
import com.ecommerce.catalgo.infrastructure.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductoDataGatewayImpl implements ProductoGateway {

    private final ProductoDataJpaRepository productoDataJpaRepository;
    private final ProductoMapper productoMapper;

    @Override
    public Producto guardarProducto(Producto producto) {
        ProductoData productoDataGuardar = productoMapper.topruductoData(producto);

        return productoMapper.topruducto(productoDataJpaRepository.save(productoDataGuardar));
    }

    @Override
    public Producto buscarProductoPorId(String productoId) {
        return productoMapper.topruducto(productoDataJpaRepository.findById(productoId).orElse(null));
    }


    @Override
    public void eliminarProductoPorId(String productoId) {
        productoDataJpaRepository.deleteById(productoId);
    }
}
