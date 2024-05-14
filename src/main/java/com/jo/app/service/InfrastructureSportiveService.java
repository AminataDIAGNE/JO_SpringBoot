package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.InfrastructureSportiveDto;

public interface InfrastructureSportiveService {

	List<InfrastructureSportiveDto> findAllInfrastructureSportives();

    void createInfrastructureSportive(InfrastructureSportiveDto infrastructureSportiveDto);

    InfrastructureSportiveDto findInfrastructureSportiveById(Long infrastructureSportiveId);

    void updateInfrastructureSportive(InfrastructureSportiveDto infrastructureSportiveDto);

    void deleteInfrastructureSportive(Long infrastructureSportiveId);

}
