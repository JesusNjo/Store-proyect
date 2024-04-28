package com.Store.Store.backend.service.product;

import com.Store.Store.backend.entity.Product;
import com.Store.Store.backend.entity.dto.ProductDTO;
import com.Store.Store.backend.entity.emuns.Category;
import com.Store.Store.backend.entity.pagination.Pagination;
import com.Store.Store.backend.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("ProductServiceImpl")
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProdutService{
    private final ProductRepository productRepository;
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Map<String, String> createProduct(ProductDTO productDTO) {
        Map<String,String> response = new HashMap<>();
        Product product = Product.builder()
                .name(productDTO.getName().toUpperCase())
                .price(productDTO.getPrice())
                .category(Category.valueOf(productDTO.getCategory().toUpperCase()))
                .description(productDTO.getDescription())
                .image(productDTO.getImage())
                .build();

        productRepository.findProductByName(product.getName()).ifPresentOrElse(ex->{
            log.error("This product already exist!");
            response.put("Product already exist",product.getName());
        },()->{
            log.info("Product was created."+product.getName());
            response.put("Product waw create",product.getName());
            productRepository.save(product);
        });

        return response;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(productRepository.findProductById(id));
    }

    @Override
    public void modifyClient(ProductDTO product, String id) {
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isPresent()){
            Product productToModify = productFound.get();
            productToModify.setName(product.getName());
            productToModify.setPrice(product.getPrice());
            productToModify.setCategory(Category.valueOf(product.getCategory().toUpperCase()));
            productToModify.setDescription(product.getDescription());

            productRepository.save(productToModify);
        }
    }

    //Query and pagination
    @Override
    public Page<Product> findProductPagination(Pagination pagination) {
        List<Product> productList= productRepository.findProductPagination(
                pagination.getProperty()[0],
                pagination.getDirection() == Sort.Direction.ASC ? 1:-1,
                pagination.getLimit(),
                pagination.getSkip()
        );
        return new PageImpl<>(productList, PageRequest.of(pagination.getSkip(), pagination.getLimit()),productList.size());
    }

    @Override
    public Page<Product> findProductQueryHandler(Pagination pagination, String query) {
        List<Product> productList= productRepository.findProductQueryHandler(
                query,
                pagination.getProperty()[0],
                pagination.getDirection() == Sort.Direction.ASC ? 1:-1,
                pagination.getSkip(),
                pagination.getLimit()
        );
        return new PageImpl<>(productList, PageRequest.of(pagination.getSkip(), pagination.getLimit()),productList.size());
    }

    @Override
    public List<Product> findProductSorted(Pagination pagination) {
        return productRepository.findProductSorted(
                pagination.getProperty()[0],
                pagination.getDirection() == Sort.Direction.ASC ? 1 : -1
        );
    }


}
