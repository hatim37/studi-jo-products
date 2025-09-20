package com.ecom.products;


import com.ecom.products.entity.Product;
import com.ecom.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.CommandLineRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

class ProductsApplicationTest {

    @Test
    void testCommandLineRunner_createsProducts_whenNotExist() throws Exception {
        // Mock du repository
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        // Mock des produits inexistants
        when(productRepository.findByName("Solo")).thenReturn(null);
        when(productRepository.findByName("Duo")).thenReturn(null);
        when(productRepository.findByName("Famille")).thenReturn(null);

        // Lancement du CommandLineRunner
        ProductsApplication app = new ProductsApplication();
        CommandLineRunner runner = app.commandLineRunner(productRepository);

        // Exécution du runner
        runner.run();

        // Vérifie que save a été appelé pour chaque produit
        verify(productRepository).save(argThat(p -> p.getName().equals("Solo")));
        verify(productRepository).save(argThat(p -> p.getName().equals("Duo")));
        verify(productRepository).save(argThat(p -> p.getName().equals("Famille")));

        // Vérifie que save été appelé 3 fois
        verify(productRepository, times(3)).save(any(Product.class));
    }
}