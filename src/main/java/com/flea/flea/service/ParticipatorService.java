package com.flea.flea.service;

import com.flea.flea.dto.response.ParticipatorResponseBase;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ParticipatorService {

    Page<ParticipatorResponseBase> getAllParticipator(Integer start, Integer off, List<String> filters);

    ParticipatorResponseBase getParticipator(String id);

}
