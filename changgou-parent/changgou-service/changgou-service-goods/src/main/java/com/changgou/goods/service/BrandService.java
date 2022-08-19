package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/22 12:23
 * @description 标题
 * @package com.changgou.goods.service
 */
public interface BrandService {

    List<Brand> findAll();


    Brand findById(Integer id);

    void add(Brand brand);

    void update(Brand brand);


    void deleteById(Integer id);


    List<Brand> search(Brand brand);


    PageInfo<Brand> findByPage(Integer page, Integer size);


    PageInfo<Brand> findByPage(Integer page, Integer size, Brand brand);
}
