package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Help;
import com.jnshu.dreamteam.utils.MyPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author draper_hxy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelpMapperTest {

    @Resource
    HelpMapper helpMapper;

    @Test
    public void testInsert() {
        Help help = new Help();
        help.setCreateAt(System.currentTimeMillis());
        help.setUpdateAt(System.currentTimeMillis());
        help.setTitle("测试帮助");
        help.setHelp("🤡");
        helpMapper.insert(help);
    }

    @Test
    public void testSelect1() {
        Help help = new Help();
        help.setId(1L);
        Help selectHelp1 = helpMapper.selectById(help.getId());
        System.out.println(selectHelp1.toString());
    }

    @Test
    public void testSelect2() {
        Help help = new Help();
        help.setId(1L);
        MyPage<Help> page = new MyPage<>(1, 8);
        QueryWrapper<Help> helpQueryWrapper = new QueryWrapper<>();
        IPage<Help> helpIPage = helpMapper.selectPage(page, helpQueryWrapper);
        List<Help> helpList = helpIPage.getRecords();
        helpList.forEach(e -> System.out.println(e.getHelp()));


    }

    @Test
    public void testDelete() {
        QueryWrapper<Help> helpQueryWrapper = new QueryWrapper<>();
        int i = helpMapper.delete(helpQueryWrapper.lambda().eq(Help::getId, 4L));
        System.out.println(i);
    }


}
