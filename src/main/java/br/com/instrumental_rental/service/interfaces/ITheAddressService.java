package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;

public interface ITheAddressService extends IService<TheAddress, Exception> {

    public TheAddress update(TheAddress theAddress) throws TheAddressNotFoundException;

    public TheAddress findById(Long addressId) throws TheAddressNotFoundException;

    public void delete(Long id) throws TheAddressNotFoundException;
}
