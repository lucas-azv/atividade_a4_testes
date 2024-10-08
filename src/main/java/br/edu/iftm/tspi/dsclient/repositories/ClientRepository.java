package br.edu.iftm.tspi.dsclient.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.dsclient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientByBirthDateBetween(Instant dataInicio, Instant dataTermino);

    List<Client> findByNameStartingWith(String prefix);

    List<Client> findByNameEndingWith(String suffix);

    List<Client> findByBirthDateBetween(Instant startDate, Instant endDate);

    List<Client> findByIncomeGreaterThan(Double income);

    List<Client> findByIncomeLessThan(Double income);

    List<Client> findByIncomeBetween(Double minIncome, Double maxIncome);

    List<Client> findByName(String name);

    List<Client> findByNameContainingIgnoreCase(String name);

    List<Client> findByChildrenGreaterThanEqual(Integer children);

    List<Client> findByCpfStartingWith(String prefix);

    @Query("SELECT c FROM Client c WHERE c.name IN (SELECT name FROM Client GROUP BY name HAVING COUNT(name) > 1)")
    List<Client> findClientsWithDuplicatedNames();

}
