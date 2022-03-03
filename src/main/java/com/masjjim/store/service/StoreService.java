package com.masjjim.store.service;

import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;


    public void createStore(Integer memNo, ReqRegister reqRegister) {
    }
}
