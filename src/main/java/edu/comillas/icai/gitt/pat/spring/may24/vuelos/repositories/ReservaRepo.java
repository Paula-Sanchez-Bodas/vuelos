package edu.comillas.icai.gitt.pat.spring.may24.vuelos.repositories;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.entity.ReservaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepo extends CrudRepository<ReservaEntity,Long> {
    List<ReservaEntity> findByUsuario(String usuario);

}
