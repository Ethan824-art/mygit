package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/22 12:23
 * @description 标题
 * @package com.changgou.goods.service.impl
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        //根据主键查询品牌数据
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        //添加数据到数据库中 insert into tb_brand(name,letter,image,seq) values(?????)
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        //更新品牌数据 update tb_brand set name=?,letter?,image=?,seq=? where id=?
        brandMapper.updateByPrimaryKeySelective(brand);
        //brandMapper.updateByPrimaryKey(brand);//不推荐使用
    }

    @Override
    public void deleteById(Integer id) {
        //删除 delete from tb_brand where id=?
        brandMapper.deleteByPrimaryKey(id);
    }

    //根据条件查询
    @Override
    public List<Brand> search(Brand brand) {
        //条件对象

        Example example = createExample(brand);
        //3.执行条件查询
        //select * from tb_brand
        List<Brand> brandList = brandMapper.selectByExample(example);


        //4.返回结果
        return brandList;

    }

    private Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        //1.判断是否为空
        if(brand!=null){
            //2.根据不同的条件组装条件对象example
            Example.Criteria criteria = example.createCriteria();

            if(!StringUtils.isEmpty(brand.getName())) {
                //参数1 指定要查询的条件的POJO的属性名
                //参数2 指定查询的条件的属性对应的值
                criteria.andLike("name", "%"+brand.getName()+"%");// where  name like '%heima%'
            }
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());// and letter='H'
            }
        }
        return example;//alt + ctr +M
    }

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer size) {
        //1.开始分页
        PageHelper.startPage(page,size);
        //2.执行查询的sql (查询所有)
        List<Brand> brandList = brandMapper.selectAll();
        //3.封装到pageInfo对象中
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);
        return pageInfo;
    }

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer size, Brand brand) {
        //1.开始分页
        PageHelper.startPage(page,size);
        //2.执行查询的sql (根据条件查询)
        Example example = createExample(brand);

        List<Brand> brandList = brandMapper.selectByExample(example);
        //3.封装到pageInfo对象中
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);
        return pageInfo;

    }
}
