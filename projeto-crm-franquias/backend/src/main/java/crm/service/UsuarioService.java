package crm.service;


import crm.model.Usuario;
import crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;

   @Autowired
   public UsuarioService(UsuarioRepository usuarioRepository) {
       this.usuarioRepository = usuarioRepository;
    }

   @Transactional
   public Usuario cadastrar(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }

   public Usuario buscarPorEmail(String email) {
       return usuarioRepository.findByEmail(email);
    }

   public List<Usuario> listarUsuarios() {
       return usuarioRepository.findAll();
    }

    @Transactional
    public void excluirUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
    
    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizar(int id, Usuario usuario) {
        return usuarioRepository.findById(id)
            .map(usuarioExistente -> {
                
                usuarioExistente.setEmail(usuario.getEmail());
                usuarioExistente.setNomeUsuario(usuario.getNomeUsuario());
                usuarioExistente.setSenha(usuario.getSenha());
                usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
                usuarioExistente.setIdFranquia(usuario.getIdFranquia());
            
                return usuarioRepository.save(usuarioExistente);
            })
            .orElse(null);
    }
}

