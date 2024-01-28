package br.com.instrumental_rental.service;

import br.com.instrumental_rental.repository.interfaces.IContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContactServiceTest {

    @Mock
    IContactRepository contactRepository;

    @InjectMocks
    ContactServiceTest contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
