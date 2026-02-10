package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String id){
        for (Product product : productData){
            if (product.getProductId().equals(id)) return product;
        }
        return null;
    }

    public Product edit(Product product){
        Product updatedProduct = findById(product.getProductId());
        if (updatedProduct == null) throw new RuntimeException("Product not found");

        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductQuantity(product.getProductQuantity());

        return product;
    }
}
