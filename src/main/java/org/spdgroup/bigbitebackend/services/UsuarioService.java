package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.entities.Usuario;
import org.spdgroup.bigbitebackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usarioRepo;

    @Override
    public void saveUsuario(Usuario usuario) {
        usarioRepo.save(usuario);
    }
}
