package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Address;
import com.ledao.entity.R;
import com.ledao.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-25 17:31
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 不分页条件查询收货地址
     *
     * @param address
     * @return
     */
    @GetMapping("/listNoPage")
    public R listNoPage(Address address) {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("customerId", address.getCustomerId());
        addressQueryWrapper.orderByDesc("isSelected");
        List<Address> addressList = addressService.list(addressQueryWrapper);
        map.put("addressList", addressList);
        return R.ok(map);
    }

    /**
     * 添加或修改收货地址
     *
     * @param address
     * @return
     */
    @PostMapping("/save")
    public R save(Address address) {
        int key;
        //id为空时添加
        if (address.getId() == null) {
            QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
            addressQueryWrapper.eq("customerId", address.getCustomerId());
            List<Address> addressList = addressService.list(addressQueryWrapper);
            //将添加的第一个地址设置为默认地址
            address.setIsSelected(addressList.size() == 0);
            key = addressService.add(address);
        } else {
            key = addressService.update(address);
        }
        if (key > 0) {
            return R.ok("保存成功");
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 设置默认地址
     *
     * @param customerId
     * @param addressId
     * @return
     */
    @PostMapping("/selectAddress")
    public void selectAddress(Integer customerId, Integer addressId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("customerId", customerId);
        List<Address> addressList = addressService.list(addressQueryWrapper);
        for (Address address : addressList) {
            //如果是默认地址
            if (address.getIsSelected()) {
                //修改成非默认地址
                address.setIsSelected(false);
                addressService.update(address);
            } else {
                //如果是目标地址
                if (addressId.equals(address.getId())) {
                    //修改成默认地址
                    address.setIsSelected(true);
                    addressService.update(address);
                }
            }
        }
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer addressId) {
        int key = addressService.delete(addressId);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        Address address = addressService.findById(id);
        map.put("address", address);
        return R.ok(map);
    }
}
