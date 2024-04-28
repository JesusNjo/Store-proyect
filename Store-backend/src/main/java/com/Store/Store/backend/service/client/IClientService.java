package com.Store.Store.backend.service.client;

import com.Store.Store.backend.entity.Client;
import com.Store.Store.backend.entity.dto.ClientDTO;
import com.Store.Store.backend.entity.pagination.Pagination;

import java.util.List;
import java.util.Map;

public interface IClientService {

    public List<Client> findAllClient();
    public Client findClientById(String id);
    public Map<String,String> createClient(ClientDTO clientDTO);
    public void deleteClientById(String id);

    public void modifyClient(ClientDTO client, String id);

    public List<Client> findClientPagin(Pagination pagination);
}
