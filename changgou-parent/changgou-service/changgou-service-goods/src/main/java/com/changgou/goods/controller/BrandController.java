package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/22 12:22
 * @description 标题
 * @package com.changgou.goods.controller
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询所有的品牌的数据
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询成功",brandList);
    }

    /**
     * 根据品牌的ID 获取品牌的数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(name="id") Integer id){
        Brand brand = brandService.findById(id);
        /*if(brand==null){
            throw new XXXX("客户不好意思，没有货了");
        }*/
        return new Result<Brand>(true,StatusCode.OK,"根据ID查询单个品牌成功",brand);
    }



    /**
     * 添加品牌的数据
     * @param brand 是页面传递过来的数据
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 根据ID 更新品牌的数据
     * @param id  要更新的品牌的主键
     * @param brand  更新后的数据
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable(name="id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 根据ID 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(name="id") Integer id){
        brandService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 条件查询
     * @param brand  页面传递的数据封装到该条件对象中
     * @return
     */
    @PostMapping("/search")
    public Result<List<Brand>> search(@RequestBody(required = false) Brand brand){
        List<Brand> brandList =brandService.search(brand);
        return new Result(true,StatusCode.OK,"条件查询成功",brandList);
    }

    /**
     * 只是分页查询
     * @param page 当前页码
     * @param size 每页显示的行
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable(name="page") Integer page,
                                              @PathVariable(name="size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findByPage(page,size);
        return new Result(true,StatusCode.OK,"分页查询成功",pageInfo);
    }

    /**
     * 条件分页查询
     * @param page
     * @param size
     * @param brand  页面传递过来的条件的参数封装对象
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable(name="page") Integer page,
                                              @PathVariable(name="size") Integer size,
                                              @RequestBody Brand brand) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page,size,brand);
        int i=1/0;
        return new Result(true,StatusCode.OK,"分页查询成功",pageInfo);

    }
}
