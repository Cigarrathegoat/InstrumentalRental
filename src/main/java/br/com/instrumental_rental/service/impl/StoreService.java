package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.repository.entities.Store;
import br.com.instrumental_rental.repository.interfaces.IStoreRepository;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements IStoreService {


    IStoreRepository storeRepository;

    @Autowired
    private StoreService(IStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    @Override
    public List<Store> saveFirstTime(List<Store> storeList) throws StoreNotFoundException {
        if(storeList.isEmpty()) throw StoreNotFoundException("S01", "Store not found");
        else {
            List<Store> savedStoreList = new ArrayList<>();
            for (Store store : storeList) {
                storeRepository.save(store);
                savedStoreList.add(store);
            }
            return savedStoreList;
        }

    }

    @Override
    public Store save(Store store) {
        return null;
    }
}
