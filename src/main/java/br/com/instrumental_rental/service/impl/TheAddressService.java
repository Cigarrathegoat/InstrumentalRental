package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;
import br.com.instrumental_rental.repository.interfaces.ITheAddressRepository;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TheAddressService implements ITheAddressService {

    ITheAddressRepository theAddressRepository;

    @Autowired
    public TheAddressService(ITheAddressRepository theAddressRepository) {
        this.theAddressRepository = theAddressRepository;
    }
    @Override
    public TheAddress save(TheAddress theAddress) {
        return theAddressRepository.save(theAddress);
    }

    public TheAddress findById(Long addressId) throws TheAddressNotFoundException {
        return theAddressRepository.findById(addressId).orElseThrow(() ->
                new TheAddressNotFoundException("A01", "Address not found"));
    }

    @Override
    public List<TheAddress> findAll() {
        return null;
    }
}
