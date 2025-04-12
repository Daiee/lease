package com.daie.lease.controller;

import cn.hutool.system.UserInfo;
import com.daie.lease.common.login.LoginUser;
import com.daie.lease.common.login.LoginUserHandler;
import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.UserCollectionPageVo;
import com.daie.lease.model.vo.UserInfoVo;
import com.daie.lease.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public Result<UserInfoVo> getUserById() throws Exception {
        LoginUser loginUser = LoginUserHandler.getLoginUser();
        UserInfoVo userInfo = userService.getUserInfoById(loginUser.getId());
        return Result.success(userInfo);
    }

    @PostMapping("/star/add")
    public ResponseEntity<Void> addToUserCollection(@RequestParam Long userId, @RequestParam Long roomId) {
        try {
            userService.addToUserCollection(userId, roomId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/star/delete")
    public ResponseEntity<Void> removeFromUserCollection(@RequestParam Long userId, @RequestParam Long roomId) {
        try {
            userService.removeFromUserCollection(userId, roomId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/star/page")
    public Result<Page<UserCollectionPageVo>> getUserCollection(@RequestParam Long userId, @RequestParam int pageNo, @RequestParam int pageSize) {
        Page<UserCollectionPageVo> starPageVo = userService.getUserCollection(userId, pageNo, pageSize);
        return Result.success(starPageVo);
    }

    @PostMapping("/{userId}/browse-history/{roomId}")
    public ResponseEntity<Void> addToBrowseHistory(@PathVariable Long userId, @PathVariable Long roomId) {
        try {
            userService.addToBrowseHistory(userId, roomId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}