package com.jo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jo.app.dto.BilletDto;
import com.jo.app.entity.Billet;
import com.jo.app.mapper.BilletMapper;
import com.jo.app.repository.BilletRepository;
import com.jo.app.service.BilletService;

@Service
public class BilletServiceImpl implements BilletService{

	private BilletRepository billetRepository;

    public BilletServiceImpl(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

	@Override
	public List<BilletDto> findAllBillets() {
		List<Billet> billets = billetRepository.findAll();
        return billets.stream().map(BilletMapper::mapToBilletDto)
                .collect(Collectors.toList());
	}

	@Override
	public void createBillet(BilletDto billetDto) {
		Billet billet = BilletMapper.mapToBillet(billetDto);
		billetRepository.save(billet);
		
	}

	@Override
	public BilletDto findBilletById(Long billetId) {
		Billet billet = billetRepository.findById(billetId).get();
		return BilletMapper.mapToBilletDto(billet);
	}

	@Override
	public void updateBillet(BilletDto billetDto) {
		Billet billet = BilletMapper.mapToBillet(billetDto);
		billetRepository.save(billet);
		
	}

	@Override
	public void deleteBillet(Long billetId) {
		billetRepository.deleteById(billetId);
		
	}

}
