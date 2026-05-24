package com.ecommerce.catalgo.application.config;

import com.ecommerce.catalgo.domain.model.gateway.ProductoGateway;
import com.ecommerce.catalgo.domain.usecase.ProductoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class UseCaseConfig {

    @Bean
    public ProductoUseCase productoUseCase(ProductoGateway productoGateway){
        return new ProductoUseCase(productoGateway);
    }

}
