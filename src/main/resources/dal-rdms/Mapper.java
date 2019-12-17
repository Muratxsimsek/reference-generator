package aero.tav.tams.reference.manager.dal.rdbms.mapper.#package#;

import aero.tav.tams.reference.manager.dal.domain.#package#.#class#;
import aero.tav.tams.reference.manager.dal.rdbms.entity.#package#.#class#Entity;
import aero.tav.tams.reference.manager.dal.rdbms.mapper.BaseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface #class#Mapper extends BaseMapper<#class#Entity, #class#> {

}