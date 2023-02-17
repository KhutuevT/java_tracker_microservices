package com.backand.tracker.modules.time_slice;

import com.backand.tracker.annotations.CurrentUser;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import com.backand.tracker.modules.time_slice.services.TimeSliceService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.time_slice.dto.req.TimeSliceStartReqDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/time-slice")
public class TimeSliceRestControllerV1 {

    private TimeSliceService timeSliceService;

    @Autowired
    public TimeSliceRestControllerV1(
            TimeSliceService timeSliceService
    ) {
        this.timeSliceService = timeSliceService;
    }

    @Operation(summary = "Возвращает все time-slice таски")
    @GetMapping
    public ResponseEntity getAll(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @PathVariable
            Principal principal
    ) {
        List<TimeSliceDto> timeSliceList = timeSliceService.getAllDtoByTaskId(taskId, new TimePoint(), new TimePoint());
        return new ResponseEntity(timeSliceList, HttpStatus.OK);
    }

    @Operation(summary = "Возвращает time-slice по id")
    @GetMapping("/{timeSliceId}")
    public ResponseEntity getById(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @PathVariable
            Long timeSliceId
    ) {
        TimeSliceDto timeSlice = timeSliceService.getDtoById(timeSliceId);

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }

    @Operation(summary = "Начинает трекать время")
    @PostMapping("/start")
    ResponseEntity start(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @RequestBody
            TimeSliceStartReqDto reqDto,
            @CurrentUser User user
    ) {
        TimeSliceDto timeSlice = timeSliceService.start(taskId, user, reqDto.getName());

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }

    @Operation(summary = "Останавливает трекание времени")
    @PostMapping("/stop")
    ResponseEntity stop(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @CurrentUser User user
    ) {
        TimeSliceDto timeSlice = timeSliceService.stop(taskId, user);

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }
}
