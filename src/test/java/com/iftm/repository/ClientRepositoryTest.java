package com.iftm.repository;

import com.iftm.client.entities.Client;
import com.iftm.client.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
public void setUp() {
    clientRepository.deleteAll();

    Client cliente1 = new Client(null, "Bruno Vieira", "12345678901", 5000.0, Instant.parse("1990-01-01T00:00:00Z"), 2);
    Client cliente2 = new Client(null, "Carlos Rangel", "98765432100", 6000.0, Instant.parse("1992-02-02T00:00:00Z"), 1);
    Client cliente3 = new Client(null, "João Vitorino", "11122334455", 7000.0, Instant.parse("1985-03-15T00:00:00Z"), 3);
    Client cliente4 = new Client(null, "Lucas Azevedo", "12468013579", 4000.0, Instant.parse("1997-02-06T00:00:00Z"), 4);
    Client cliente5 = new Client(null, "Vinicius Raphael", "11357924680", 3500.0, Instant.parse("1972-08-31T00:00:00Z"), 5);

    clientRepository.save(cliente1);
    clientRepository.save(cliente2);
    clientRepository.save(cliente3);
    clientRepository.save(cliente4);
    clientRepository.save(cliente5);
}

// Testes Lucas Azevedo :)

@Test
public void testBuscarClientesComSalarioSuperior() {
    List<Client> clientes = clientRepository.findByIncomeGreaterThan(5500.0);
    assertEquals(2, clientes.size());
    assertTrue(clientes.stream().anyMatch(cliente -> "Carlos Rangel".equals(cliente.getName())));
    assertTrue(clientes.stream().anyMatch(cliente -> "João Vitorino".equals(cliente.getName())));
}

@Test
public void testBuscarClientesComSalarioInferior() {
    List<Client> clientes = clientRepository.findByIncomeLessThan(4000.0);
    assertEquals(1, clientes.size());
    assertEquals("Vinicius Raphael", clientes.get(0).getName());
}

@Test
public void testBuscarClientesComSalarioEntre() {
    List<Client> clientes = clientRepository.findByIncomeBetween(4500.0, 7500.0);
    assertEquals(3, clientes.size());
    assertTrue(clientes.stream().anyMatch(cliente -> "Bruno Vieira".equals(cliente.getName())));
    assertTrue(clientes.stream().anyMatch(cliente -> "Carlos Rangel".equals(cliente.getName())));
    assertTrue(clientes.stream().anyMatch(cliente -> "João Vitorino".equals(cliente.getName())));
}
}
