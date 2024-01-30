package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;

public interface ITheAddressService extends IService<TheAddress, Exception> {

    public TheAddress findById(Long addressId) throws TheAddressNotFoundException;
}
