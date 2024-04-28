package com.Store.Store.backend.controller.product;

import com.Store.Store.backend.entity.Product;
import com.Store.Store.backend.entity.dto.ProductDTO;
import com.Store.Store.backend.entity.pagination.Pagination;
import com.Store.Store.backend.entity.querys.ProductQuery;
import com.Store.Store.backend.service.product.IProdutService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final IProdutService produtService;


    @GetMapping
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> productList = produtService.findAllProducts();
        return !productList.isEmpty()
                ?ResponseEntity.ok(productList)
                :ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable String id){
        Product product = produtService.findProductById(id);
        return product != null ? ResponseEntity.ok(product): ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Map<String,String>> createProduct(@RequestBody ProductDTO productDTO){
        Map<String,String> createProduct = produtService.createProduct(productDTO);
        return ResponseEntity.ok(createProduct);
    }

    //Query and pagination
    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> findAllPagClient(@NotNull Pagination pageable) {
        Page<Product> page = produtService.findProductPagination(pageable);

        return !page.isEmpty()?ResponseEntity.ok(page.getContent()): ResponseEntity.noContent().build();
    }

    @GetMapping("/query")
    public ResponseEntity<List<Product>> filterClientQuery(@RequestParam ProductQuery query, Pagination pagination){
        Page<Product> page = produtService.findProductQueryHandler(pagination,query.getQuery().toUpperCase());
        return !page.isEmpty()?ResponseEntity.ok(page.getContent()):ResponseEntity.noContent().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Product>> findSortProduct(Pagination pagination){
        List<Product> page = produtService.findProductSorted(pagination);
        return !page.isEmpty()?ResponseEntity.ok(page):ResponseEntity.noContent().build();
    }
}
