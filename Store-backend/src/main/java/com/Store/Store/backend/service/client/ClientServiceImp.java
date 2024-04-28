package com.Store.Store.backend.service.client;

import com.Store.Store.backend.entity.Client;
import com.Store.Store.backend.entity.dto.ClientDTO;
import com.Store.Store.backend.entity.emuns.Gender;
import com.Store.Store.backend.entity.pagination.Pagination;
import com.Store.Store.backend.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("ClientServiceImp")
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImp implements IClientService{

    private final ClientRepository clientRepository;
    @Override
    public List<Client> findAllClient() {
       return clientRepository.findAll();
    }

    @Override
    public Client findClientById(String id) {
        return clientRepository.findClientById(id);
    }

    @Override
    public Map<String,String> createClient(ClientDTO clientDTO) {
        Map<String,String> response = new HashMap<>();
        Client client = Client.builder()
                .name(clientDTO.getName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .password(clientDTO.getPassword())
                .address(clientDTO.getAddress())
                .gender(Gender.valueOf(clientDTO.getGender().toUpperCase()))
                .build();
        clientRepository.findClientByEmail(client.getEmail()).ifPresentOrElse(present->{
            log.error("This email is already exist "+client.getEmail());
            response.put(client.getEmail(),"Already exist");
        },()->{
            log.info("Client was created successfull.. "+client.getName());
            clientRepository.save(client);
            response.put(client.toString(),"was created");

        });
        return response;
    }


    @Override
    public void deleteClientById(String id) {
        clientRepository.delete(
                clientRepository.findClientById(id)
        );
    }

    @Override
    public void modifyClient(ClientDTO client, String id) {
        Optional<Client> clientFound = clientRepository.findById(id);
        if(clientFound.isPresent()){
            Client clientToModify = clientFound.get();
            clientToModify.setName(client.getName());
            clientToModify.setLastName(client.getLastName());
            clientToModify.setEmail(client.getEmail());
            clientToModify.setGender(Gender.valueOf(client.getGender()));
            clientToModify.setAddress(client.getAddress());
            clientToModify.setPassword(client.getPassword());

            clientRepository.save(clientToModify);
        }

    }

    //Pagination and querys

    @Override
    public List<Client> findClientPagin(Pagination pagination){
        return clientRepository.findClientPagin(
                pagination.getProperty()[0],
                pagination.getDirection() == Sort.Direction.ASC ? 1 : -1,
                pagination.getLimit(),
                pagination.getSkip()
        );
    }
}
