package com.igloosec.app.controller;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.*;
import com.igloosec.app.exceptions.DtoValidationException;
import com.igloosec.app.service.InStateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@RestController
@RequestMapping("/instate")
public class InStateRestController {
    private static final Logger logger = LogManager.getLogger(InStateRestController.class);

    @Autowired
    InStateService inStateService;

    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> createInState(@PathVariable String userId, @Valid @RequestBody InState request, BindingResult bindingResult) throws DtoValidationException {
        logger.error(request.toString());

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult.getFieldErrors());
        }

        ResultResponse resResult = new ResultResponse();

        try {
            resResult = inStateService.createInState(userId, request);
        } catch (Exception ex) {
            resResult.setCode("I003");
            resResult.setMessage("입추정보 등록 실패");

            return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{userId}/{targetDate}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> updateInState(@PathVariable String userId, @PathVariable String targetDate, @Valid @RequestBody InState request, BindingResult bindingResult) throws DtoValidationException {
        logger.error(request.toString());

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult.getFieldErrors());
        }

        ResultResponse resResult = new ResultResponse();

        try {
            resResult = inStateService.updateInState(userId, targetDate, request);
        } catch (Exception ex) {
            resResult.setCode("I003");
            resResult.setMessage("입추정보 수정 실패");

            return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Instates> getInStateList(@PathVariable String userId) throws DtoValidationException {
        List<InstateResponse> instateList = new ArrayList<>();

        try {
            instateList = inStateService.getInstateList(userId);
            logger.error("instatelist: " + instateList.size());
        } catch (Exception ex) {

        }

        Instates instates = new Instates();

        for (InstateResponse instate : instateList) {
            logger.error("instate: " + instate.toString());
            instates.getInstate().add(instate);
        }

        return new ResponseEntity<Instates>(instates, HttpStatus.OK);
    }
}
