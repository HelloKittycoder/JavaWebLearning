package com.bjsxt.extension;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shucheng on 2020/6/8 22:52
 * 参考链接：https://www.jianshu.com/p/a88b18475f78
 * https://blog.csdn.net/Dongguabai/article/details/90246717
 * https://www.jianshu.com/p/8773d0e786d8
 */
public class MapResultHandler implements ResultHandler {

    private final Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext resultContext) {
        Object object = resultContext.getResultObject();
        if (object instanceof Map) {
            Map map = (Map) resultContext.getResultObject();
            mappedResults.put(map.get("key"), map.get("value"));
        }
    }

    public Map getMappedResults() {
        return mappedResults;
    }
}
