package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.repository.entities.Store;

import java.util.List;

public interface IStoreService {

    List<Store> saveFirstTime(List<Store> storeList);

    Store save(Store store);
}
