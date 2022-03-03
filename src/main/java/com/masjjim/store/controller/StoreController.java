package com.masjjim.store.controller;

import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.store.service.StoreService;
import com.masjjim.util.network.Header;
import com.masjjim.util.security.domain.CustomUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @ApiOperation("생성")
    @PostMapping
    public Header<Void> createStore(@AuthenticationPrincipal CustomUser customUser, @RequestBody ReqRegister reqRegister) throws Exception {
        storeService.createStore(customUser.getMemNo(),reqRegister);
        return Header.CREATE();
    }

}
