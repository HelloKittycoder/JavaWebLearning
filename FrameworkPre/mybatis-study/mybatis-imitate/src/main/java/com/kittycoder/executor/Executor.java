package com.kittycoder.executor;

import com.kittycoder.mapping.MapStatement;

import java.util.List;

/**
 * Created by shucheng on 2019-9-21 下午 16:22
 */
public interface Executor {

    <E> List<E> query(MapStatement ms, Object parameter);
}
