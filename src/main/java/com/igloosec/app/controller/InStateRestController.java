package com.igloosec.app.controller;

import com.igloosec.app.dto.request.InState;
import com.igloosec.app.dto.response.ResultResponse;
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
}
