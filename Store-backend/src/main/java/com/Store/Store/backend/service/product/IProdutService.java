package com.Store.Store.backend.service.product;

import com.Store.Store.backend.entity.Product;
import com.Store.Store.backend.entity.dto.ProductDTO;
import com.Store.Store.backend.entity.pagination.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IProdutService {

    public List<Product> findAllProducts();
    public Product findProductById(String id);
    public Map<String,String> createProduct(ProductDTO product);
    public void deleteProduct(String id);
    public void modifyClient(ProductDTO product, String id);
    public Page<Product> findProductPagination(Pagination pagination);
    public Page<Product> findProductQueryHandler(Pagination pagination,String query);
    public List<Product> findProductSorted(Pagination pagination);
}
