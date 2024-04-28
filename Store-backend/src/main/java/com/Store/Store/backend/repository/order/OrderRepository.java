package com.Store.Store.backend.repository.order;

import com.Store.Store.backend.entity.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Aggregation({
            "{$match : {_id: ?0}}"
    })
    Order findOrderById(String id);
}
