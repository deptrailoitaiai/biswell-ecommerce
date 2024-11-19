package org.example.config;

import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class DeleteNullDtoToEntityConfig {
    @Bean()
    public ModelMapper entityNonNull() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Condition<?, ?> notNull = Conditions.isNotNull();
        modelMapper.getConfiguration().setPropertyCondition(notNull);
        return modelMapper;
    }
}
