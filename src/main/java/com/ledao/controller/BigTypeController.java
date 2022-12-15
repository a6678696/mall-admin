package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.BigType;
import com.ledao.entity.R;
import com.ledao.entity.SmallType;
import com.ledao.service.BigTypeService;
import com.ledao.service.SmallTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 商品大类Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 21:47
 */
@RestController
@RequestMapping("/bigType")
public class BigTypeController {

    @Resource
    private BigTypeService bigTypeService;

    @Resource
    private SmallTypeService smallTypeService;

    /**
     * 分页条件查询商品大类
     *
     * @param bigType
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(BigType bigType, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<BigType> queryWrapper = new QueryWrapper<>();
        if (bigType.getName() != null) {
            queryWrapper.like("name", bigType.getName());
        }
        queryWrapper.orderByAsc("sortNum");
        Page<BigType> bigTypePage = new Page<>(currentPage, pageSize);
        map.put("bigTypeList", bigTypeService.list(queryWrapper, bigTypePage));
        map.put("total", bigTypeService.getCount(queryWrapper));
        return R.ok(map);
    }

    /**
     * 添加或修改商品大类
     *
     * @param bigType
     * @return
     */
    @PostMapping("/save")
    public R save(BigType bigType) {
        int key;
        if (bigType.getId() == null) {
            key = bigTypeService.add(bigType);
        } else {
            key = bigTypeService.update(bigType);
        }
        if (key > 0) {
            return R.ok("保存成功");
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 根据id查找商品大类
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("bigType", bigTypeService.findById(id));
        return R.ok(map);
    }

    /**
     * 删除商品大类
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(Integer id) {
        int key = bigTypeService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 获取全部商品大类(在商品小类中使用)
     *
     * @return
     */
    @GetMapping("/getAllBigType")
    public R getAllBigType() {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<BigType> bigTypeQueryWrapper = new QueryWrapper<>();
        bigTypeQueryWrapper.orderByAsc("sortNum");
        List<BigType> bigTypeList = bigTypeService.list(bigTypeQueryWrapper);
        for (BigType bigType : bigTypeList) {
            QueryWrapper<SmallType> smallTypeQueryWrapper = new QueryWrapper<>();
            smallTypeQueryWrapper.orderByAsc("sortNum");
            smallTypeQueryWrapper.eq("bigTypeId", bigType.getId());
            bigType.setSmallTypeList(smallTypeService.list(smallTypeQueryWrapper));
        }
        Iterator<BigType> bigTypeIterator = bigTypeList.listIterator();
        while (bigTypeIterator.hasNext()) {
            if (bigTypeIterator.next().getSmallTypeList().size()==0) {
                bigTypeIterator.remove();
            }
        }
        map.put("allBigTypeList", bigTypeList);
        return R.ok(map);
    }
}
