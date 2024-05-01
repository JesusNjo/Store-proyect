package com.Store.Store.backend.repository.client;

import com.Store.Store.backend.entity.Client;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client,String> {

    @Aggregation({
            "{$match: {_id: ?0}}"
    })
    Client findClientById(String id);

    @Aggregation({
            "{$match: {email: ?0}}"
    })
    Optional<Client> findClientByEmail(String email);

    @Aggregation({
            "{$sort : { ?0 : ?1 }}",
            "{$limit : ?2}",
            "{$skip: ?3}"
    })
    List<Client> findClientPagin(String property,int direction,int limit, int skip);

    @Aggregation({
            "{$match: {username: ?0}}"
    })
    Optional<Client> findByUsername(String username);




}
