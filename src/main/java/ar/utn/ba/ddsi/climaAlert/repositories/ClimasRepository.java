package ar.utn.ba.ddsi.climaAlert.repositories;

import ar.utn.ba.ddsi.climaAlert.dto.Clima;

import java.util.List;

public interface ClimasRepository {

    public List<Clima> findAlll();

    public void save (Clima clima);

    public Clima findById(Long id);

    public void delete(Long id);
}
