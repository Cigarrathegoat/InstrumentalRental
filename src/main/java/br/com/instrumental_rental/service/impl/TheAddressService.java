package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.repository.entities.TheAddress;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TheAddressService implements ITheAddressService {

    ITheAddressRepository theAddressService;

    @Autowired
    public TheAddressService(ITheAddressService theAddressService) {
        this.theAddressService = theAddressService;
    }
    @Override
    public TheAddress save(TheAddress theAddress) {
        return ;
    }

    @Override
    public TheAddress findById(Long addressID) throws AddressNotFoundException {

    }

    @Override
    public List<TheAddress> findAll() {
        return null;
    }
}
