package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.BigType;
import com.ledao.entity.Goods;
import com.ledao.entity.R;
import com.ledao.entity.SmallType;
import com.ledao.service.BigTypeService;
import com.ledao.service.GoodsService;
import com.ledao.service.SmallTypeService;
import com.ledao.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * 商品Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-16 13:35
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Value("${goodsDetailsImageFilePath}")
    private String goodsDetailsImageFilePath;

    @Value("${cardImageFilePath}")
    private String cardImageFilePath;

    @Value("${goodsDetailsSwiperImageFilePath}")
    private String goodsDetailsSwiperImageFilePath;

    @Resource
    private GoodsService goodsService;

    @Resource
    private BigTypeService bigTypeService;

    @Resource
    private SmallTypeService smallTypeService;

    /**
     * 分页条件查询商品
     *
     * @param goods
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(Goods goods, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>(16);
        Page<Goods> goodsPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
        if (goods.getName() != null) {
            goodsQueryWrapper.like("name", goods.getName());
        }
        if (goods.getSmallTypeId() != null) {
            goodsQueryWrapper.eq("smallTypeId", goods.getSmallTypeId());
        }
        map.put("goodsList", goodsService.list(goodsQueryWrapper, goodsPage));
        map.put("total", goodsService.getCount(goodsQueryWrapper));
        return R.ok(map);
    }

    /**
     * 添加或修改商品
     *
     * @param goods
     * @return
     */
    @PostMapping("/save")
    public R save(Goods goods) {
        int key;
        if (goods.getId() == null) {
            goods.setSwiperGoods(false);
            goods.setHotGoods(false);
            goods.setSalesVolume(0);
            goods.setCardImageName("default.jpg");
            goods.setSwiperImageName("default.jpg");
            SmallType smallType = smallTypeService.findById(goods.getSmallTypeId());
            BigType bigType = bigTypeService.findById(smallType.getBigTypeId());
            goods.setTypeName(bigType.getName() + "/" + smallType.getName());
            key = goodsService.add(goods);
        } else {
            goods.setSmallTypeId(goods.getSmallTypeId());
            SmallType smallType = smallTypeService.findById(goods.getSmallTypeId());
            BigType bigType = bigTypeService.findById(smallType.getBigTypeId());
            goods.setTypeName(bigType.getName() + "/" + smallType.getName());
            key = goodsService.update(goods);
        }
        if (key > 0) {
            return R.ok("保存成功");
        } else {
            return R.ok("保存失败");
        }
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Goods goods = goodsService.findById(id);
        if (!"default.jpg".equals(goods.getCardImageName())) {
            FileUtils.deleteQuietly(new File(cardImageFilePath + goods.getCardImageName()));
        }
        int key = goodsService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.ok("删除失败");
        }
    }

    /**
     * 改变商品的热卖状态
     *
     * @param id
     * @return
     */
    @PostMapping("/changeHotGoodsStatus")
    public R changeHotGoodsStatus(Integer id) {
        Goods goods = goodsService.findById(id);
        goods.setHotGoods(!goods.getHotGoods());
        goodsService.update(goods);
        if (goods.getHotGoods()) {
            return R.ok("商品已经被设置为热卖");
        } else {
            return R.ok("商品已经被设置为非热卖");
        }
    }

    /**
     * 改变商品的热卖状态
     *
     * @param id
     * @return
     */
    @PostMapping("/changeSwiperGoodsStatus")
    public R changeSwiperGoodsStatus(Integer id) {
        Goods goods = goodsService.findById(id);
        goods.setSwiperGoods(!goods.getSwiperGoods());
        goodsService.update(goods);
        if (goods.getSwiperGoods()) {
            return R.ok("商品已经被设置为首页轮播图商品");
        } else {
            return R.ok("商品已经被设置为非首页轮播图商品");
        }
    }

    /**
     * 根据id获取商品
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        Goods goods = goodsService.findById(id);
        map.put("goods", goods);
        SmallType smallType = smallTypeService.findById(goods.getSmallTypeId());
        BigType bigType = bigTypeService.findById(smallType.getBigTypeId());
        int[] typeId = new int[]{bigType.getId(), smallType.getId()};
        map.put("typeId", typeId);
        return R.ok(map);
    }

    /**
     * wangEditor富文本编辑器上传图片
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/wangEditorUploadImage")
    public Map<String, Object> wangEditorUploadImage(@RequestParam("image") MultipartFile multipartFile) throws Exception {
        //给图片定义一个名称
        String newFileName = DateUtil.getCurrentDateStr2() + System.currentTimeMillis() + ".jpg";
        assert multipartFile != null;
        //实现将图片保存到指定位置
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(goodsDetailsImageFilePath + "/" + newFileName));
        //返回指定的格式给前端使用
        Map<String, Object> map = new HashMap<>(16);
        map.put("errno", 0);
        //返回的数据,wangEditor接收
        Map<String, String> data = new HashMap<>(16);
        data.put("url", "http://localhost:8080/image/goods/details/" + newFileName);
        data.put("alt", newFileName);
        data.put("href", "http://localhost:8080/image/goods/details/" + newFileName);
        map.put("data", data);
        return map;
    }

    /**
     * 设置商品卡片图片
     *
     * @param file
     * @param goodsId
     * @return
     * @throws Exception
     */
    @PostMapping("/setCardImage")
    public void setCardImage(MultipartFile file, Integer goodsId) throws Exception {
        //给图片定义一个名称
        String newFileName = DateUtil.getCurrentDateStr2() + System.currentTimeMillis() + ".jpg";
        assert file != null;
        //实现将图片保存到指定位置
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(cardImageFilePath + newFileName));
        Goods goods = goodsService.findById(goodsId);
        String oldCardImageName = goods.getCardImageName();
        goods.setCardImageName(newFileName);
        goodsService.update(goods);
        if (!"default.jpg".equals(oldCardImageName)) {
            FileUtils.deleteQuietly(new File(cardImageFilePath + oldCardImageName));
        }
    }

    /**
     * 上传商品详情轮播图图片
     *
     * @param file
     * @param goodsId
     * @return
     * @throws Exception
     */
    @PostMapping("/updateGoodsDetailsSwiperImage")
    public void updateGoodsDetailsSwiperImage(MultipartFile file, Integer goodsId) throws Exception {
        //给图片定义一个名称
        String newFileName = DateUtil.getCurrentDateStr2() + System.currentTimeMillis() + ".jpg";
        assert file != null;
        //实现将图片保存到指定位置
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(goodsDetailsSwiperImageFilePath + newFileName));
        Goods goods = goodsService.findById(goodsId);
        String newGoodsDetailsSwiperImageStr = goods.getGoodsDetailsSwiperImageStr() + "," + newFileName;
        System.out.println(newGoodsDetailsSwiperImageStr);
        goods.setGoodsDetailsSwiperImageStr(newGoodsDetailsSwiperImageStr);
        goodsService.update(goods);
    }

    /**
     * 获取商品详情图片名称列表
     *
     * @param goodsId
     * @return
     */
    @GetMapping("/getGoodsDetailsSwiperImageNameList")
    public Map<String, Object> getGoodsDetailsSwiperImageNameList(Integer goodsId) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Goods goods = goodsService.findById(goodsId);
        List<String> imageNameLsit = Arrays.asList(goods.getGoodsDetailsSwiperImageStr().split(","));
        Collections.reverse(imageNameLsit);
        resultMap.put("imageNameLsit", imageNameLsit);
        return resultMap;
    }

    /**
     * 删除商品详情轮播图图片并修改数据库中的数据
     *
     * @param goodsId
     * @param imageName
     */
    @PostMapping("/deleteGoodsDetailsSwiperImage")
    public void deleteGoodsDetailsSwiperImage(Integer goodsId, String imageName) {
        FileUtils.deleteQuietly(new File(goodsDetailsSwiperImageFilePath + imageName));
        Goods goods = goodsService.findById(goodsId);
        goods.setGoodsDetailsSwiperImageStr(goods.getGoodsDetailsSwiperImageStr().replaceAll("," + imageName, ""));
        goodsService.update(goods);
    }
}
