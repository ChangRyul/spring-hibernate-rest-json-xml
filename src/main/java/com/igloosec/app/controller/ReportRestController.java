package com.igloosec.app.controller;

import com.igloosec.app.dto.request.Report;
import com.igloosec.app.dto.response.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enrico on 9/5/14.
 */
@RestController
@RequestMapping("/report")
public class ReportRestController {

    private static final Logger logger = LogManager.getLogger(ReportRestController.class);

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> createReport(@PathVariable String userId, @Valid @RequestBody Report request, BindingResult bindingResult) throws DtoValidationException {
        logger.error(request.toString());

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult.getFieldErrors());
        }

        ResultResponse resResult = new ResultResponse();

        try {
            resResult = reportService.createReport(userId, request);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            resResult.setCode("R003");
            resResult.setMessage("리포트 등록 실패");

            return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{userId}/{reportDate}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResultResponse> updateReport(@PathVariable String userId, @PathVariable String reportDate, @Valid @RequestBody Report request, BindingResult bindingResult) throws DtoValidationException {
        logger.error(request.toString());

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult.getFieldErrors());
        }

        ResultResponse resResult = new ResultResponse();

        try {
            resResult = reportService.updateReport(userId, reportDate, request);
        } catch (Exception ex) {
            resResult.setCode("R003");
            resResult.setMessage("리포트 수정 실패");

            return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<ResultResponse>(resResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/{userId}/{obcode}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reports> getReportList(@PathVariable String userId, @PathVariable String obcode) throws DtoValidationException {
        List<ReportResponse> reportList = new ArrayList<>();

        try {
            reportList = reportService.getReportList(userId, obcode);
            logger.error("reportlist: " + reportList.size());
        } catch (Exception ex) {

        }

        Reports reports = new Reports();

        for (ReportResponse report : reportList) {
            logger.error("report: " + report.toString());
            reports.getReport().add(report);
        }

        return new ResponseEntity<Reports>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/obcode/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Obcodes> getObcodeList(@PathVariable String userId) throws Exception {
        List<Obcode> obcodeList = new ArrayList<>();

        try {
            obcodeList = reportService.getUserObcodeList(userId);
        } catch (Exception ex) {

        }

        Obcodes obcodes = new Obcodes();

        for (Obcode obcode : obcodeList) {
            obcodes.getObcode().add(obcode);
        }

        if (obcodes.getObcode().size() == 0) {
//            obcodes.setCode("C002");
//            obcodes.setMessage("건물 정보 없음");

            return new ResponseEntity<Obcodes>(obcodes, HttpStatus.OK);
        } else {
//            obcodes.setCode("C001");
//            obcodes.setMessage("건물 정보 조회 성공");
            return new ResponseEntity<Obcodes>(obcodes, HttpStatus.OK);
        }
    }

}
