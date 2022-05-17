package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.Menu;
import com.john.user.app.mapper.MenuMapper;
import com.john.user.app.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author john
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
