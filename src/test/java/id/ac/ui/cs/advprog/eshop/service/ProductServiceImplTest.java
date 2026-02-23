package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        Product created = productService.create(product);
        assertNotNull(created.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    void testFindByIdPositive() {
        when(productRepository.findById("123")).thenReturn(product);
        Product found = productService.findById("123");
        assertEquals(product, found);
    }

    @Test
    void testFindByIdNegative_ThrowsException() {
        when(productRepository.findById("123")).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> productService.findById("123"));
    }

    @Test
    void testEdit() {
        when(productRepository.edit(product)).thenReturn(product);
        Product edited = productService.edit(product);
        assertEquals(product, edited);
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDelete() {
        when(productRepository.findById("123")).thenReturn(product);
        when(productRepository.delete("123")).thenReturn(product);
        Product deleted = productService.delete("123");
        assertEquals(product, deleted);
        verify(productRepository, times(1)).delete("123");
    }
}