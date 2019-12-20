package aero.tav.tams.reference.manager.app.controller.#package#;

import aero.tav.tams.reference.manager.app.controller.BaseController;
import aero.tav.tams.reference.manager.app.dto.#package#.#class#DTO;
import aero.tav.tams.reference.manager.app.mapper.#package#.#class#DTOMapper;
import aero.tav.tams.reference.manager.app.service.#package#.#class#Service;
import aero.tav.tams.reference.manager.dal.domain.#package#.#class#;
import aero.tav.tams.reference.manager.dal.filter.#package#.#class#Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class #class#Controller extends BaseController<#class#, #class#DTO, #class#Filter, #class#DTOMapper, #class#Service> {

    @Autowired
    private #class#DTOMapper #class#DTOMapper;

    @Autowired
    private #class#Service #class#Service;

    @Override
    protected #class#DTOMapper getMapper() {
        return #class#DTOMapper;
    }

    @Override
    protected #class#Service getService() {
        return #class#Service;
    }

}