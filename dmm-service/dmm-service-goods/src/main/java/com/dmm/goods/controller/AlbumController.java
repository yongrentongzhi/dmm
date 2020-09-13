package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Album;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.feign.FileFeign;
import com.dmm.goods.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/goods/album", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumController {
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private UidGenerator uidGenerator;
    @Autowired
    private FileFeign fileFeign;

    @PostMapping
    public Result save(@RequestBody Album album) {
        album.setId(uidGenerator.getUID());
        albumService.save(album);
        return new Result(StatusCode.OK, true, "保存相册成功");
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        Album album = albumService.getById(id);
        String[] strings = album.getImage().split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
        }
        albumService.removeById(id);
        fileFeign.deleteFile(strings[1], stringBuilder.toString());
        return new Result(StatusCode.OK, true, "删除相册成功");
    }
    @PutMapping
    public Result update(@RequestBody Album album) {
        albumService.updateById(album);
        return new Result(StatusCode.OK, true, "修改相册成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Album album = albumService.getById(id);
        return new Result(StatusCode.OK, true, "查询相册成功", album);
    }

    @PostMapping("/search/{albumName}/{pageNum}/{size}")
    public Result<Page<Album>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("albumName") String albumName) {
        QueryWrapper<Album> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(albumName)) {
            wrapper.likeRight("name", albumName);
        }
        Page<Album> page = new Page<>(pageNum, size);
        Page<Album> albumPage = albumService.page(page, wrapper);

        return new Result<Page<Album>>(StatusCode.OK, true, "按名称搜索相册列表", albumPage);
    }
}
