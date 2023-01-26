package com.backand.tracker.modules.time_slice;

import com.backand.tracker.modules.time_slice.services.TimeSliceService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.time_slice.dto.req.TimeSliceStartReqDto;
import com.backand.tracker.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/time-slice")
public class TimeSliceRestControllerV1 {

    private TimeSliceService timeSliceService;
    private UserRepository userRepository;

    @Autowired
    public TimeSliceRestControllerV1(
            TimeSliceService timeSliceService,
            UserRepository userRepository
    ) {
        this.timeSliceService = timeSliceService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity getAll(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(
                principal.getName()).get();
        return null;
    }

    @GetMapping("/{timeSliceId}")
    public ResponseEntity getById(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @PathVariable
            Long timeSliceId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(
                principal.getName()).get();
        TimeSlice timeSlice = timeSliceService.getById(
                timeSliceId);
        return new ResponseEntity(timeSlice,
                                  HttpStatus.OK
        );
    }

    @PostMapping("/start")
    ResponseEntity start(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @RequestBody
            TimeSliceStartReqDto reqDto,
            Principal principal
    ) {
        User user = userRepository.findByUsername(
                principal.getName()).get();
        TimeSlice timeSlice = timeSliceService
                .start(user,
                       projectId,
                       taskId,
                       reqDto.getName()
                );

        return new ResponseEntity(timeSlice,
                                  HttpStatus.OK
        );
    }

    @PostMapping("/stop")
    ResponseEntity stop(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(
                principal.getName()).get();
        TimeSlice timeSlice = timeSliceService.stop(
                user,
                projectId,
                taskId
        );
        return new ResponseEntity(timeSlice,
                                  HttpStatus.OK
        );
    }
}
