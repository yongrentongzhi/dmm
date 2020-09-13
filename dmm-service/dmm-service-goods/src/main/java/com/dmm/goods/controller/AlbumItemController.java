package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.AlbumItem;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.feign.FileFeign;
import com.dmm.goods.service.IAlbumItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/goods/albumItem", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumItemController {
    @Autowired
    private IAlbumItemService albumItemService;
    @Autowired
    private UidGenerator uidGenerator;
    @Autowired
    private FileFeign fileFeign;

    @PostMapping
    public Result save(@RequestBody AlbumItem albumItem) {
        albumItem.setId(uidGenerator.getUID());
        albumItemService.save(albumItem);
        return new Result(StatusCode.OK, true, "保存相片成功");
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {

        AlbumItem albumItem=albumItemService.getById(id);
        String[] strings = albumItem.getImage().split("/");
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=2;i<strings.length;i++) {
            stringBuilder.append(strings[i]);
        }
        albumItemService.removeById(id);
        fileFeign.deleteFile(strings[1],stringBuilder.toString());
        return new Result(StatusCode.OK, true, "删除相片成功");
    }

    @PutMapping
    public Result update(@RequestBody AlbumItem albumItem) {
        albumItemService.updateById(albumItem);
        return new Result(StatusCode.OK, true, "修改相片成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        AlbumItem albumItem = albumItemService.getById(id);
        return new Result(StatusCode.OK, true, "查询相片成功", albumItem);
    }

    @PostMapping("/search/{albumItemName}/{pageNum}/{size}")
    public Result<Page<AlbumItem>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("albumItemName") String albumItemName) {
        QueryWrapper<AlbumItem> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(albumItemName)) {
            wrapper.likeRight("name", albumItemName);
        }
        Page<AlbumItem> page = new Page<>(pageNum, size);
        Page<AlbumItem> albumItemPage = albumItemService.page(page, wrapper);

        return new Result<Page<AlbumItem>>(StatusCode.OK, true, "按名称搜索相片列表", albumItemPage);
    }
}
