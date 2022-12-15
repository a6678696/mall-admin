package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.BigType;
import com.ledao.entity.SmallType;
import com.ledao.entity.R;
import com.ledao.service.BigTypeService;
import com.ledao.service.SmallTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品小类Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 23:01
 */
@RestController
@RequestMapping("/smallType")
public class SmallTypeController {

    @Resource
    private SmallTypeService smallTypeService;

    @Resource
    private BigTypeService bigTypeService;

    /**
     * 分页条件查询商品小类
     *
     * @param smallType
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(SmallType smallType, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<SmallType> queryWrapper = new QueryWrapper<>();
        if (smallType.getName() != null) {
            queryWrapper.like("name", smallType.getName());
        }
        if (smallType.getBigTypeId() != null) {
            queryWrapper.eq("bigTypeId", smallType.getBigTypeId());
        }
        queryWrapper.orderByAsc("sortNum");
        Page<SmallType> smallTypePage = new Page<>(currentPage, pageSize);
        map.put("smallTypeList", smallTypeService.list(queryWrapper, smallTypePage));
        map.put("total", smallTypeService.getCount(queryWrapper));
        return R.ok(map);
    }

    /**
     * 添加或修改商品小类
     *
     * @param smallType
     * @return
     */
    @PostMapping("/save")
    public R save(SmallType smallType) {
        int key;
        BigType bigType = bigTypeService.findById(smallType.getBigTypeId());
        smallType.setBigTypeName(bigType.getName());
        if (smallType.getId() == null) {
            key = smallTypeService.add(smallType);
        } else {
            key = smallTypeService.update(smallType);
        }
        if (key > 0) {
            return R.ok("保存成功");
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 根据id查找商品小类
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("smallType", smallTypeService.findById(id));
        return R.ok(map);
    }

    /**
     * 删除商品小类
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(Integer id) {
        int key = smallTypeService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
