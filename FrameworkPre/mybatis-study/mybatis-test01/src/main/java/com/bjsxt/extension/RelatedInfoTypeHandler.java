package com.bjsxt.extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bjsxt.pojo.RelatedInfo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shucheng on 2020/6/5 22:34
 * 参考链接：https://www.iteye.com/blog/elim-1847854
 */
public class RelatedInfoTypeHandler extends BaseTypeHandler<RelatedInfo> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RelatedInfo parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public RelatedInfo getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getRelatedInfo(rs.getString(columnName));
    }

    @Override
    public RelatedInfo getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getRelatedInfo(rs.getString(columnIndex));
    }

    @Override
    public RelatedInfo getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getRelatedInfo(cs.getString(columnIndex));
    }

    // 获取RelatedInfo
    private RelatedInfo getRelatedInfo(String columnValue) {
        if (columnValue == null) {
            return null;
        }
        RelatedInfo relatedInfo = JSON.parseObject(columnValue, new TypeReference<RelatedInfo>(){});
        relatedInfo.setOriginalJson(columnValue);
        return relatedInfo;
    }
}
