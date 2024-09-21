package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;
import org.spdgroup.bigbitebackend.model.entities.Usuario;
import org.spdgroup.bigbitebackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // TODO: LUEGO AGREGAR UNA CLASE ExceptionHandler PARA MANEJAR LAS EXCEPCIONES A NIVEL GLOBAL
    @Override
    public void registrar(UsuarioDTO usuarioDTO ) throws Exception {
        // Verificar si el email ya está registrado
        if (usuarioRepo.findByEmail(usuarioDTO.getEmail()) != null) {
            throw new Exception("Ya existe un usuario registrado con el email ingresado");
        }

        // Crear el usuario y guardarlo en la base de datos
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .email(usuarioDTO.getEmail())
                .telefono(usuarioDTO.getTelefono())
                .password(usuarioDTO.getPassword()) // En un entorno real, deberías encriptar la contraseña
                .build();
        usuarioRepo.save(usuario);
    }
}
