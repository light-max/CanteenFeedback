package com.pyk.canteen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pyk.canteen.controller.mobile.CollectController;
import com.pyk.canteen.mapper.AccountMapper;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.service.DishService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CanteenApplicationTests {
    @Resource
    CollectController collectController;

    @Resource
    AccountMapper accountMapper;

    @Resource
    DishService dishService;

    @Test
    void contextLoads() {
        Random r = new Random();
        List<Account> accounts = accountMapper.selectList(new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getType, 2)
                .or(accountLambdaQueryWrapper -> accountLambdaQueryWrapper.eq(Account::getType,3)));
        for (Account account : accounts) {
            for (Dish dish : dishService.list()) {
                if (r.nextBoolean()) {
                    System.out.println("true");
                    if (!collectController.check(account, dish.getId()).getData()) {
                        collectController.collect(account, dish.getId());
                    }
                }
            }
        }
    }

}
