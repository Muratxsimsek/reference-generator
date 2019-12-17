package aero.tav.tams.reference.manager.dal.rdbms.repository.adapter.#package#;

import aero.tav.tams.reference.manager.dal.adapter.#package#.#class#Adapter;
import aero.tav.tams.reference.manager.dal.domain.#package#.#class#;
import aero.tav.tams.reference.manager.dal.filter.#package#.#class#Filter;
import aero.tav.tams.reference.manager.dal.rdbms.entity.#package#.#class#Entity;
import aero.tav.tams.reference.manager.dal.rdbms.mapper.#package#.#class#Mapper;
import aero.tav.tams.reference.manager.dal.rdbms.repository.adapter.BaseAdapterImpl;
import aero.tav.tams.reference.manager.dal.rdbms.repository.#package#.#class#Repository;
import aero.tav.tams.reference.manager.dal.rdbms.specification.#package#.#class#FilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class #class#AdapterImpl extends
        BaseAdapterImpl<#class#Entity, #class#, #class#Filter, #class#Repository, #class#Mapper, #class#FilterSpecification>
        implements #class#Adapter {

    @Autowired
    private #class#Repository #instance#Repository;

    @Autowired
    private #class#Mapper #instance#Mapper;

    @Autowired
    private #class#FilterSpecification #instance#FilterSpecification;

    @Override
    public #class#Repository getRepository() {
        return #instance#Repository;
    }

    @Override
    public #class#Mapper getMapper() {
        return #instance#Mapper;
    }

    @Override
    protected #class#FilterSpecification getSpecification() {
        return #instance#FilterSpecification;
    }

    @Override
    public Class<#class#> forClass() {
        return #class#.class;
    }

}
