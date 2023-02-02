package com.backand.tracker.modules.report;

import com.backand.tracker.modules.report.dto.req.GetReportReqDto;
import com.backand.tracker.modules.report.service.ReportService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/reports")
public class ReportRestControllerV1 {

    UserService userService;
    ReportService reportService;

    @Autowired
    ReportRestControllerV1(
            ReportService reportService,
            UserService userService
    ) {
        this.reportService = reportService;
        this.userService = userService;
    }

//   @GetMapping("/")
//   public ResponseEntity<?> getReport(
//           @PathVariable GetReportReqDto getReportReqDto,
//           Principal principal
//   ) {
//        User user = userService.getUserByUsername(principal.getName());
//        //...
//        return new ResponseEntity<>("test", HttpStatus.OK);
//   }
}
