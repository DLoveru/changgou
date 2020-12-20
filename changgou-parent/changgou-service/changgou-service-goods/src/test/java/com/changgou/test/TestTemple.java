package com.changgou.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.changgou.GoodsApplication;
import com.changgou.entity.IdWorker;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = GoodsApplication.class)
@RunWith(SpringRunner.class)
public class TestTemple {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private SkuMapper mapper;

    @Autowired
    private IdWorker idWorker;

    @Test
    public void test01() {
//        Template byCategory = templateService.findByCategory(43);
//        System.out.println(byCategory.toString());
        for (int i = 1; i <= 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }
    @Test
    void test() {
        Page<Sku> mpPage = mapper.selectPage(new Page<>(1, 2), Wrappers.<Sku>query().eq("id", 1));
        assertThat(mpPage.getTotal()).isEqualTo(1L);
        List<Sku> records = mpPage.getRecords();
        assertThat(records).isNotEmpty();
        assertThat(records.size()).isEqualTo(1);

        // pagehelper
        PageInfo<Sku> info = PageHelper.startPage(1, 2).doSelectPageInfo(() -> mapper.selectById(1));
        assertThat(info.getTotal()).isEqualTo(1L);
        List<Sku> list = info.getList();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void testIn() {
        List<Long> ids = Arrays.asList(1L, 2L);
        Page<Sku> mpPage = mapper.selectPage(new Page<>(1, 5), Wrappers.<Sku>query().in("id", ids));
        assertThat(mpPage.getTotal()).isEqualTo(2L);
        List<Sku> records = mpPage.getRecords();
        assertThat(records).isNotEmpty();
        assertThat(records.size()).isEqualTo(2);

        // pagehelper
        PageInfo<Sku> info = PageHelper.startPage(1, 5)
                .doSelectPageInfo(() -> mapper.selectList(Wrappers.<Sku>query().in("id", ids)));
        assertThat(info.getTotal()).isEqualTo(2L);
        List<Sku> list = info.getList();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(2);
    }
}
