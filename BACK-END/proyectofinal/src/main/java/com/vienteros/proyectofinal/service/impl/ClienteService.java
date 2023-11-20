package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.ClienteDTO;
import com.vienteros.proyectofinal.model.Cliente;
import com.vienteros.proyectofinal.repository.ClienteRepository;
import com.vienteros.proyectofinal.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<ClienteDTO> listarClientesUsuario(int idUsuario) {
        List<Cliente> clientes = repository.findByProyectoUsuarioId(idUsuario);
        List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> {
            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .documento(cliente.getDocumento())
                    .build();
            return clienteDTO;
        }).collect(Collectors.toList());
        return clientesDTO;
    }

    @Override
    public List<ClienteDTO> listarClientes(int idProyecto) {
        List<Cliente> clientes = repository.findByProyectoId(idProyecto);
        List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> {
            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .documento(cliente.getDocumento())
                    .build();
            return clienteDTO;
        }).collect(Collectors.toList());
        return clientesDTO;
    }

    @Override
    public ClienteDTO crearCliente(Cliente cliente) {
        Cliente cliente1 = repository.save(cliente);
        ClienteDTO clienteDTO = ClienteDTO.builder()
                .id(cliente1.getId())
                .nombre(cliente1.getNombre())
                .documento(cliente1.getDocumento())
                .build();
        return clienteDTO;
    }

}
