package com.aftas.backend.services.implServices;

import com.aftas.backend.models.dtos.level.LevelDto;
import com.aftas.backend.models.entities.Level;
import com.aftas.backend.repository.levelRepository;
import com.aftas.backend.services.interfaceServices.levelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class levelServiceImpl implements levelService {

    @Autowired
    private levelRepository Repo_Level;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LevelDto saveLevel(LevelDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);

        validatePoints(level);

        Level savedLevel = Repo_Level.save(level);

        return modelMapper.map(savedLevel, LevelDto.class);
    }

    private void validatePoints(Level level) {
        Integer pointsOfLatestLevel = Repo_Level.findPointsOfLatestLevel();

        if (pointsOfLatestLevel != null) {
            if (level.getPoints() < pointsOfLatestLevel) {
                throw new IllegalArgumentException("Le nombre de points doit être plus élevé que le niveau précédent.");
            }
        }
    }


}
