package com.exos.services.controller;

import com.exos.services.domain.UserVO;
import com.exos.services.exception.RecordAlreadyExistException;
import com.exos.services.exception.RecordNotFoundException;
import com.exos.services.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "User", description = "The User API")
@Validated
public class UserController {

    @Autowired
    UserService userService;
    @Operation(summary = "Save Bulk Users", description = "Used to Add a bulk users in one go")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Users created", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/bulk")
    public ResponseEntity<Set<UserVO>> saveBulkUsers(@RequestBody @Valid Set<UserVO> userVOList) {
        log.info("Processing save users request for User {}", userVOList);
        if(CollectionUtils.isEmpty(userVOList)) {
            throw new RuntimeException("Something went wrong!!!");
        }else{
            Set<UserVO> savedUsers = userService.saveUsers(userVOList);
            return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Add a new user", description = "Used to Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created", content = @Content(schema = @Schema(implementation = UserVO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping
    public ResponseEntity<UserVO> saveUser(@RequestBody @Valid UserVO userVO) {
        log.info("Processing save user request for User {}", userVO);
        UserVO extUser = userService.getUserById(userVO.getEmail());
        if(extUser != null){
            throw new RecordAlreadyExistException("User Already Exist");
        }
        UserVO user = userService.saveUser(userVO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(summary = "Find User By Id", description = "Used to Find User By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Found", content = @Content(schema = @Schema(implementation = UserVO.class))),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @GetMapping(value = "/{email}")
    public ResponseEntity<UserVO> getUserById (@PathVariable("email") String email) {
        UserVO userVO = userService.getUserById(email);
        if(userVO == null) {
            throw new RecordNotFoundException("Invalid user email : " + email);
        }
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    @Operation(summary = "Update User By Email", description = "Used to Update User By Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Found", content = @Content(schema = @Schema(implementation = UserVO.class))),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @PutMapping(value = "/{email}")
    public ResponseEntity<UserVO> getUserById (@PathVariable("email") String email, @Valid @RequestBody UserVO userVO) {
        UserVO extUser = userService.updateUser(userVO, email);
        if(userVO == null) {
            throw new RecordNotFoundException("Invalid user email : " + email);
        }
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }
}
