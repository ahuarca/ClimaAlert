package ar.utn.ba.ddsi.climaAlert.repositories;

import ar.utn.ba.ddsi.climaAlert.dto.Clima;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClimasRepositoryImpl implements ClimasRepository {

    ArrayList<Clima> climas = new ArrayList<>();

    @Override
    public List<Clima> findAlll() {
        return climas;
    }

    @Override
    public void save (Clima clima) {
        Long nuevoId = (long) climas.size();
        clima.setId(nuevoId);
        climas.add(clima);
    }
    @Override
    public Clima findById(Long id){
        return climas.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public void delete(Long id){
        climas.removeIf(c -> c.getId().equals(id));
    }
}
