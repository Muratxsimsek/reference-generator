package aero.tav.tams.reference.manager.app.service.#package#;

import aero.tav.tams.reference.manager.app.service.BaseService;
import aero.tav.tams.reference.manager.dal.adapter.#package#.#class#Adapter;
import aero.tav.tams.reference.manager.dal.domain.#package#.#class#;
import aero.tav.tams.reference.manager.dal.filter.#package#.#class#Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class #class#Service extends BaseService<#class#, #class#Filter> {

    @Autowired
    private #class#Adapter #instance#Adapter;

    @Override
    public #class#Adapter getAdapter() {
        return #instance#Adapter;
    }

}