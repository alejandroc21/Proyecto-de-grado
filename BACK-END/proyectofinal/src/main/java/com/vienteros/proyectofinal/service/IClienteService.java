package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.ClienteDTO;
import com.vienteros.proyectofinal.model.Cliente;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> listarClientes(int idProyecto);

    ClienteDTO crearCliente(Cliente cliente);

    List<ClienteDTO> listarClientesUsuario(int idUsuario);
}
