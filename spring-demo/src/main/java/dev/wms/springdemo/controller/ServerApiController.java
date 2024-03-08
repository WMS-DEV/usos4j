package dev.wms.springdemo.controller;

import dev.wms.springdemo.service.serverapi.UsosTermService;
import dev.wms.usos4j.model.terms.UsosTerm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/serverapi")
public class ServerApiController {

    private final UsosTermService termService;

    @GetMapping("/term")
    public UsosTerm term(@RequestParam(required = false) String termId) {
        return termService.getTerm(termId);
    }

}