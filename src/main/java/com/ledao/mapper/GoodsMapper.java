package com.ledao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledao.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-16 13:26
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取7天内发布的商品
     *
     * @return
     */
    @Select("SELECT * FROM t_goods t WHERE ADDDATE(t.`addDate`,INTERVAL 7 DAY)>NOW() ORDER BY t.`addDate` DESC;")
    List<Goods> getNewGoodsList();

    /**
     * 获取热卖商品
     *
     * @return
     */
    @Select("SELECT * FROM t_goods WHERE hotGoods=TRUE ORDER BY setHotGoodsDate DESC;")
    List<Goods> getHotGoodsList();

    /**
     * 获取降价的商品
     *
     * @return
     */
    @Select("SELECT * FROM t_goods WHERE priceOld IS NOT NULL AND price<priceOld;")
    List<Goods> getPriceDropGoodsList();
}
