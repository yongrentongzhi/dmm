package com.dmm.user.controller;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.jwt.JwtUtil;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.user.User;
import com.dmm.common.uid.UidGenerator;
import com.dmm.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UidGenerator uidGenerator;

    /***
     * User分页条件搜索实现
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/{name}/{page}/{size}")
    public Result<Page<User>> findPageByName(@PathVariable String name,
                                       @PathVariable int page,
                                       @PathVariable int size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(name), "name", name);
        Page<User> userIPage = new Page<>(page, size);
        Page<User> pageInfo = userService.page(userIPage, queryWrapper);
        return new Result(StatusCode.OK, true, "查询成功", pageInfo);
    }


    /***
     * 根据ID删除用户数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用UserService实现根据主键删除
        userService.removeById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @return
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        //设置主键值

        //调用UserService实现修改User
        userService.updateById(user);
        return new Result(StatusCode.OK,true,  "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping
    public Result add(@RequestBody User user) {
        user.setId(uidGenerator.getUID());
        //调用UserService实现添加User

        userService.save(user);
        return new Result(StatusCode.OK, true, "添加成功");
    }


    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable String id) {
        //调用UserService实现根据主键查询User
        User user = userService.getById(id);
        return new Result<User>(StatusCode.OK, true, "查询成功", user);
    }



    /***
     *
     * 希望 拥有admin的角色人才能访问.
     * 查询User全部数据
     * @return
     */
    @PreAuthorize(value = "hasAuthority('goods_list')")
     @GetMapping
    public Result<List<User>> findAll(HttpServletRequest request) {

            //调用UserService实现查询所有User
        List<User> list = userService.list();
        return new Result<List<User>>( StatusCode.OK,true, "查询成功", list);
    }

    @RequestMapping("/login")
    public Result login(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        //1.从数据库中查询用户名对应的用户的对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            //2.判断用户是否为空 为空返回数据
            return new Result<User>(StatusCode.LOGIN_ERROR,false,  "用户名或密码错误");
        }

        //3如果不为空格 判断 密码是否正确 正确则登录成功

        if (BCrypt.checkpw(password, user.getPassword())) {
            //成功
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("role", "USER");
            info.put("success", "SUCCESS");
            info.put("username", username);

            //1.生成令牌
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            //2.设置cookie中
            Cookie cookie = new Cookie("Authorization", jwt);
            response.addCookie(cookie);
            //3.设置头文件中
            response.setHeader("Authorization", jwt);

            return new Result( StatusCode.OK,true, "成功", jwt);
        } else {
            //失败
            return new Result(StatusCode.LOGIN_ERROR,false,  "用户名或密码错误");
        }


    }




}
