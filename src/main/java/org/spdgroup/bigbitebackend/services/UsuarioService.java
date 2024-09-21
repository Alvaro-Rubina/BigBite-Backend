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
    public void registrarUsuario(UsuarioDTO usuarioDTO ) throws Exception {

        if (usuarioRepo.findByEmail(usuarioDTO.getEmail()) != null) {
            throw new Exception("Ya existe un usuario registrado con el email ingresado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .email(usuarioDTO.getEmail())
                .telefono(usuarioDTO.getTelefono())
                .password(usuarioDTO.getPassword())
                .build();
        usuarioRepo.save(usuario);
    }

    // TODO: Este método cambiará cuando se implemente la seguridad
    @Override
    public void autenticarUsuario(UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario = usuarioRepo.findByEmail(usuarioDTO.getEmail());
        if (usuario == null) {
            throw new Exception("No existe un usuario registrado con el email ingresado");
        }
        if (!usuario.getPassword().equals(usuarioDTO.getPassword())) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }

    }
}
