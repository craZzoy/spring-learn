package com.segfault.handle;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.segfault.entity.Description;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型转换器
 */
public class DescriptionTypeHandle implements TypeHandler {

    //jackson
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        StringWriter stringWriter = new StringWriter();
        try {
            //将java对象转化为流
            objectMapper.writeValue(stringWriter, parameter);
            String desc = stringWriter.toString();
            ps.setString(i, desc);
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String desc = rs.getString(columnName);
        Description description = null;
        try {
            if (StringUtils.hasText(desc)) {
                description = objectMapper.readValue(desc, Description.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return description;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String desc = rs.getString(columnIndex);
        Description description = null;
        try {
            if (StringUtils.hasText(desc)) {
                description = objectMapper.readValue(desc, Description.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return description;
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
