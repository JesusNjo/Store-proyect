package com.Store.Store.backend.repository.product;

import com.Store.Store.backend.entity.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {


    @Aggregation({
            "{$match: {_id:?0}}"
    })
    Product findProductById(String id);
    @Aggregation({
            "{$match: {name:?0}}"
    })
    Optional<Product> findProductByName(String name);

    @Aggregation({
            "{$sort: {?0:?1}}",
            "{$limit : ?2}",
            "{$skip: ?3}"

    })
    List<Product> findProductPagination(String property,int direction,int limit,int skip);

    @Aggregation(pipeline = {
            "{$match: {$or: [{ 'name': { $regex: ?0, $options: 'i' } }, { 'category': { $regex: ?0, $options: 'i' } }]}}",
            "{$sort: { ?1: ?2 }}",
            "{$skip: ?3}",
            "{$limit: ?4}"
    })
    public List<Product> findProductQueryHandler(String query, String property, int direction, int limit, int skip);

    @Aggregation(pipeline = {
            "{$sort: { ?0: ?1 }}"
    })
    List<Product> findProductSorted(String property, int direction);





}
