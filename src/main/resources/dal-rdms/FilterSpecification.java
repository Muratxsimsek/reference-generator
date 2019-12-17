package aero.tav.tams.reference.manager.dal.rdbms.specification.#package#;

import aero.tav.tams.reference.manager.dal.filter.#package#.#class#Filter;
import aero.tav.tams.reference.manager.dal.rdbms.entity.#package#.#class#Entity;
import aero.tav.tams.reference.manager.dal.rdbms.specification.BaseFilterSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class #class#FilterSpecification extends BaseFilterSpecification<#class#Entity, #class#Filter> {

    @Override
    public Specification<#class#Entity> filter(#class#Filter filter) {
        return null;
    }

}