package com.Store.Store.backend.controller.client;

import com.Store.Store.backend.entity.Client;
import com.Store.Store.backend.entity.dto.ClientDTO;
import com.Store.Store.backend.entity.pagination.Pagination;
import com.Store.Store.backend.service.client.ClientServiceImp;
import com.Store.Store.backend.service.client.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private final IClientService clientService;


    @GetMapping()
    public ResponseEntity<List<Client>> findAllClient(){
        List<Client> clientList = clientService.findAllClient();
        return !clientList.isEmpty()
        ?ResponseEntity.ok(clientList)
        :ResponseEntity.noContent().build();
    }
    @GetMapping("/id")
    public ResponseEntity<Client> findClientById(@RequestParam String id){
        Client clientFound = clientService.findClientById(id);
        if(clientFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientFound);
    }
    @PostMapping()
    public ResponseEntity<Map<String,String>> createClient(@RequestBody ClientDTO clientDTO) {
        Map<String,String> createdClient = clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    //Pagination and query

    @GetMapping("/pagination")
    public ResponseEntity<List<Client>> findClientPagination(@ModelAttribute Pagination pagination) {
        List<Client> clientListPagination = clientService.findClientPagin(pagination);

        return !clientListPagination.isEmpty() ?
                ResponseEntity.ok(clientListPagination) : ResponseEntity.noContent().build();
    }


}
