package com.john.user.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.john.boot.common.dto.PageRequest;
import com.john.boot.mysql.util.PageUtil;
import com.john.user.app.entity.Menu;
import com.john.user.app.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * menu api
 *
 * @author john
 */
@Api(tags = "menu")
@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @ApiOperation("page")
    @GetMapping("/page")
    public IPage<Menu> getPage(PageRequest request) {
        return menuService.page(PageUtil.getPage(request));
    }

    @ApiOperation("get one")
    @GetMapping("/{id}")
    public Menu getOne(@PathVariable String id) {
        return menuService.getById(id);
    }

    @ApiOperation("save one")
    @PostMapping
    public void save(@RequestBody Menu menu) {
        menuService.save(menu);
    }

    @ApiOperation("update one")
    @PutMapping
    public void update(@RequestBody Menu menu) {
        menuService.updateById(menu);
    }

    @ApiOperation("delete")
    @DeleteMapping
    public void delete(Set<String> ids) {
        menuService.removeByIds(ids);
    }
}
