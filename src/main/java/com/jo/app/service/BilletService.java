package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.BilletDto;

public interface BilletService {

	List<BilletDto> findAllBillets();

    void createBillet(BilletDto billetDto);

    BilletDto findBilletById(Long billetId);

    void updateBillet(BilletDto billetDto);

    void deleteBillet(Long billetId);

}
