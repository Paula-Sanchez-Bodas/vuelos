package edu.comillas.icai.gitt.pat.spring.may24.vuelos.repositories;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.entity.ReservaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo extends CrudRepository<ReservaEntity,Long> {

}
