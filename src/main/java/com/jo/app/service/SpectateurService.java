package com.jo.app.service;

import java.util.List;

import com.jo.app.dto.SpectateurDto;

public interface SpectateurService {

	List<SpectateurDto> findAllSpectateurs();

    void createSpectateur(SpectateurDto spectateurDto);

    SpectateurDto findSpectateurById(Long spectateurId);

    void updateSpectateur(SpectateurDto spectateurDto);

    void deleteSpectateur(Long spectateurId);

}
