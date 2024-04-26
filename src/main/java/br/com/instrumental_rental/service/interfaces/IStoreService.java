package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Store;

import java.util.List;

public interface IStoreService {

    List<Store> saveFirstTime(List<Store> storeList) throws StoreNotFoundException;

    Store save(Store store) throws StoreNotFoundException;
}
