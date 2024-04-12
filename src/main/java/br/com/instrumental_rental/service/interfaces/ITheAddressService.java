package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;

import java.util.List;

public interface ITheAddressService extends IService<TheAddress, Exception> {

    TheAddress save(TheAddress theAddress);

    List<TheAddress> saveFirstTime(List<TheAddress> theAddressList);

    TheAddress update(TheAddress theAddress) throws TheAddressNotFoundException;

    TheAddress findById(Long addressId) throws TheAddressNotFoundException;

    void delete(Long id) throws TheAddressNotFoundException;
}
