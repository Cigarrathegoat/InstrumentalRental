package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;
import br.com.instrumental_rental.repository.interfaces.ITheAddressRepository;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<TheAddress> saveFirstTime(List<TheAddress> theAddressList) {
        List<TheAddress> savedAddresses = new ArrayList<>();
        for (TheAddress theAddress : theAddressList) {
            TheAddress savedAddress = theAddressRepository.save(theAddress);
            savedAddresses.add(savedAddress);
        }
        return savedAddresses;
    }

    public TheAddress findById(Long addressId) throws TheAddressNotFoundException {
        return theAddressRepository.findById(addressId).orElseThrow(() ->
                new TheAddressNotFoundException("A01", "Address not found"));
    }

    @Override
    public List<TheAddress> findAll() {
        return theAddressRepository.findAll();
    }

    @Override
    public TheAddress update(TheAddress theAddress) throws TheAddressNotFoundException {
        var addressToUpdate = findById(theAddress.getAddressId());
        addressToUpdate.setStreetNumber(theAddress.getStreetNumber());
        addressToUpdate.setStreetName(theAddress.getStreetName());
        addressToUpdate.setBorough(theAddress.getBorough());
        addressToUpdate.setCity(theAddress.getCity());
        addressToUpdate.setState(theAddress.getState());
        addressToUpdate.setCountry(theAddress.getCountry());
        addressToUpdate.setZipCode(theAddress.getZipCode());

        return save(addressToUpdate);
    }

    public void delete(Long id) throws TheAddressNotFoundException {
        var addressToDelete = findById(id);
        theAddressRepository.delete(addressToDelete);
    }
}
