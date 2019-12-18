package aero.tav.tams.reference.manager.app.mapper.#package#;

import aero.tav.tams.reference.manager.app.dto.#package#.AirlineDTO;
import aero.tav.tams.reference.manager.app.mapper.BaseMapper;
import aero.tav.tams.reference.manager.dal.domain.#package#.Airline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface #class#DTOMapper extends BaseMapper<#class#, #class#DTO> {
}