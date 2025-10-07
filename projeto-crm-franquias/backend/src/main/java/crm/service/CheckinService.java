package crm.service;

import crm.model.Checkin;
import crm.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {

    private final CheckinRepository checkinRepository;

    @Autowired
    public CheckinService(CheckinRepository checkinRepository) {
        this.checkinRepository = checkinRepository;
    }

    @Transactional
    public Checkin salvar(Checkin checkin) {
        if (checkin.getId() != null) {
            Optional<Checkin> existente = checkinRepository.findById(checkin.getId());
            if (existente.isEmpty()) {
                return null;
            }
        }
        return checkinRepository.save(checkin);
    }

    public List<Checkin> buscarTodos() {
        return checkinRepository.findAll();
    }

    public Optional<Checkin> buscarPorId(Integer id) {
        return checkinRepository.findById(id);
    }

    @Transactional
    public void deletar(Integer id) {
        checkinRepository.deleteById(id);
    }

    public List<Checkin> buscarPorCliente(Integer clienteId) {
        return checkinRepository.findByClienteId(clienteId);
    }

    public List<Checkin> buscarPorFranquia(Integer idFranquia) {
        return checkinRepository.findByIdFranquia(idFranquia);
    }
}
