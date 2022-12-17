package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Announcement;
import com.ledao.entity.R;
import com.ledao.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 2:48
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Resource
    private AnnouncementService announcementService;

    /**
     * 分页条件查询公告
     *
     * @param announcement
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public R list(Announcement announcement, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        if (announcement.getTitle() != null) {
            queryWrapper.like("title", announcement.getTitle());
        }
        queryWrapper.orderByDesc("addDate");
        Page<Announcement> announcementPage = new Page<>(page, size);
        List<Announcement> announcementList = announcementService.list(queryWrapper, announcementPage);
        Long total = announcementService.getCount(queryWrapper);
        map.put("announcementList", announcementList);
        map.put("total", total);
        return R.ok(map);
    }

    /**
     * 添加或修改公告
     *
     * @param announcement
     * @return
     */
    @PostMapping("/save")
    public R save(Announcement announcement) {
        int key;
        if (announcement.getId() == null) {
            announcement.setAddDate(new Date());
            key = announcementService.add(announcement);
        } else {
            key = announcementService.update(announcement);
        }
        if (key > 0) {
            return R.ok("保存成功");
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 根据id查找公告
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("announcement", announcementService.findById(id));
        return R.ok(map);
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        int key = announcementService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
