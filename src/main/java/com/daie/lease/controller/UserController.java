package com.daie.lease.controller;

import com.daie.lease.model.pojo.User;
import com.daie.lease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<Page<Long>> getUserCollection(@RequestParam Long userId, @RequestParam int pageNo, @RequestParam int pageSize) {
        try {
            Page<Long> roomIds = userService.getUserCollection(userId, pageNo, pageSize);
            return ResponseEntity.ok(roomIds);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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