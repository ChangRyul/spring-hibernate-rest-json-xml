package com.igloosec.app.controller;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.ReportResponse;
import com.igloosec.app.dto.response.ResultResponse;
import com.igloosec.app.exceptions.DtoValidationException;
import com.igloosec.app.service.ReportService;
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
 * Created by enrico on 9/5/14.
 */
@RestController
@RequestMapping("/report")
public class ReportRestController {

    private static final Logger logger = LogManager.getLogger(ReportRestController.class);

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportResponse> getReport(@PathVariable String userId) throws Exception {
        logger.debug("api get for reportResponse" + userId);
        ReportResponse reportResponse = reportService.getReportDetails(userId);

        return new ResponseEntity<ReportResponse>(reportResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> createReport(@PathVariable String userId, @Valid @RequestBody Report request, BindingResult bindingResult) throws DtoValidationException {
        logger.error("report upload request");
        logger.error(request.toString());

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult.getFieldErrors());
        }

        ResultResponse resResult = reportService.createReport(userId, request);

        return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
    }

}
