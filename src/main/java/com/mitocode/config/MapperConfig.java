package com.mitocode.config;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean("medicMapper")
    public ModelMapper medicMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<MedicDTO, Medic> typeMap = mapper.createTypeMap(MedicDTO.class, Medic.class);
        typeMap.addMapping(MedicDTO::getPrimaryName, (dest, v) -> dest.setFirstName((String) v));
        typeMap.addMapping(MedicDTO::getSurname, (dest, v) -> dest.setLastName((String) v));
        typeMap.addMapping(MedicDTO::getPhoto, (dest, v) -> dest.setPhotoUrl((String) v));
        return mapper;
    }
}
