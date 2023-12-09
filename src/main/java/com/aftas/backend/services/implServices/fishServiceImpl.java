package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.models.dtos.fish.FishDto;
import com.aftas.backend.models.entities.Fish;
import com.aftas.backend.models.entities.Level;
import com.aftas.backend.repository.fishRepository;
import com.aftas.backend.repository.levelRepository;
import com.aftas.backend.services.interfaceServices.fishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class fishServiceImpl implements fishService {

    @Autowired
    private fishRepository Repo_fish;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private levelRepository Repo_level;

    @Override
    public FishDto saveFish(FishDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);

        if (fishDto.getLevel_id() != null) {
            Level level = Repo_level.findById(fishDto.getLevel_id())
                    .orElseThrow(() -> new NotFoundException("The level with id " + fishDto.getLevel_id() + " is not found"));
            fish.setLevel(level);
        }

        Fish savedFish = Repo_fish.save(fish);

        return modelMapper.map(savedFish, FishDto.class);
    }
}
