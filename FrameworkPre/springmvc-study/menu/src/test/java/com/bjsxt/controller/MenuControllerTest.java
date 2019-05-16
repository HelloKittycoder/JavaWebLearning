package com.bjsxt.controller;

import com.bjsxt.BaseTest;
import com.bjsxt.pojo.Menu;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
public class MenuControllerTest extends BaseTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void show() throws Exception {
        String responseText = mockMvc.perform(get("/show"))
            .andDo(print())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString();

        List<Menu> list = convertJsonStrToList(responseText, Menu.class);
        System.out.println(list);
    }

    /**
     * jackson将json字符串转换成List
     * 参考链接：https://www.jianshu.com/p/aa4e1d60be5b
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> convertJsonStrToList(String jsonStr, Class<T> clazz) throws Exception {
        JavaType javaType = getCollectionType(List.class, clazz);;
        return mapper.readValue(jsonStr, javaType);
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}