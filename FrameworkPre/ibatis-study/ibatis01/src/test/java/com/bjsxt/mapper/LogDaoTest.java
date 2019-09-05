package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Log;
import org.junit.Test;

import java.util.List;

/**
 * Created by shucheng on 2019-9-5 下午 19:47
 */
public class LogDaoTest extends BaseTest {

    // 查询列表
    @Test
    public void test() throws Exception {
        List<Log> list = sqlMapClient.queryForList("findAll");
        System.out.println(list);
    }

    // prepend：给语句前面添加and
    @Test
    public void testFindByAttr() throws Exception {
        Log log = new Log();
        log.setId(1);
        log.setAccOut("1");
        log.setAccIn("3");
        List<Log> list = sqlMapClient.queryForList("findByAttr", log);
        System.out.println(list);
    }

    @Test
    public void testFindByAttrWithDynamic1() throws Exception {
        Log log = new Log();
        log.setId(1);
        log.setAccOut("1");
        log.setAccIn("3");
        List<Log> list = sqlMapClient.queryForList("findByAttrWithDynamic1", log);
        // 对照findByAttrWithDynamic，可以知道拼出来的语句为：
        // select * from log where id = 1 and accout like '%1%' and accin like '%3%'
        System.out.println(list);
    }

    // 虽然下面这个是报错的，但从下面这个例子，可以看出dynamic和prepend结合使用时的规则：
    // dynamic会把第一个有prepend的and（动态标签中的and）给忽略掉，这里就是accout部分
    // 无法忽略掉静态的and，这里就是id部分
    // 参考链接：https://zhidao.baidu.com/question/385063576.html
    // https://yq.aliyun.com/articles/67689
    @Test
    public void testFindByAttrWithDynamic2() throws Exception {
        Log log = new Log();
        log.setId(1);
        log.setAccOut("1");
        log.setAccIn("3");
        List<Log> list = sqlMapClient.queryForList("findByAttrWithDynamic2", log);
        // 对照findByAttrWithDynamic，可以知道拼出来的语句为：
        // select * from log where and id = 1 accout like '%1%' and accin like '%3%'
        System.out.println(list);
    }
}
