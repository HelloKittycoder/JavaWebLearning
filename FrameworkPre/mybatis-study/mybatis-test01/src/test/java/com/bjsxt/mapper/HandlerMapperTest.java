package com.bjsxt.mapper;

import com.bjsxt.BaseTest;
import com.bjsxt.extension.MapResultHandler;
import com.bjsxt.pojo.HandlerInfo;
import com.bjsxt.pojo.RelatedInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2020/6/5 22:34
 */
public class HandlerMapperTest extends BaseTest {

    private HandlerMapper handlerMapper;

    @Before
    public void init() throws Exception {
        handlerMapper = session.getMapper(HandlerMapper.class);
    }

    @Test
    public void queryHandlerInfoList() {
        List<HandlerInfo> list = handlerMapper.queryHandlerInfoList();
        for (HandlerInfo info : list) {
            System.out.println(info);
        }
    }

    @Test
    public void testInsertHandlerInfo() {
        HandlerInfo handlerInfo = new HandlerInfo("", "王五", "1", "50");
        RelatedInfo relatedInfo = new RelatedInfo("", "一年级5班", "张翠山");
        handlerInfo.setRelatedInfo(relatedInfo);
        handlerMapper.insertHandlerInfo(handlerInfo);
        System.out.println(handlerInfo);
        session.commit();
    }

    @Test
    public void testUpdateHandlerInfo() {
        HandlerInfo handlerInfo = new HandlerInfo("1", "张三1", "1", "51");
        RelatedInfo relatedInfo = new RelatedInfo("", "一年级1班（呵呵）", "张三丰（hh）");
        handlerInfo.setRelatedInfo(relatedInfo);
        handlerMapper.updateHandlerInfo(handlerInfo);
        System.out.println(handlerInfo);
        session.commit();
    }

    @Test
    public void testSelectIdNameMap() {
        MapResultHandler resultHandler = new MapResultHandler();
        handlerMapper.selectIdNameMap(resultHandler);
        Map<String, String> resultMap = resultHandler.getMappedResults();
        System.out.println(resultMap);
    }
}
