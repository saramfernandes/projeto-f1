package senai.f1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PistaRepositoryTest {

    @Autowired
    private PistaRepository pistaRepository;

    @Test
    void deveBuscarPorPais() {

    }

}