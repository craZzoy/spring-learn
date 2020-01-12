package com.segfault.controller;

import com.segfault.domin.User;
import com.segfault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
public class JdbcController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Map<String, Object>> users() {
        return jdbcTemplate.execute(new StatementCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInStatement(Statement stmt) throws SQLException, DataAccessException {
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM USER ");
                ResultSetMetaData metaData = resultSet.getMetaData();
                ArrayList<String> columnNames = new ArrayList<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                List<Map<String, Object>> result = new LinkedList<>();

                while (resultSet.next()) {
                    Map<String, Object> map = new HashMap<>();
                    for (String columnName : columnNames) {
                        Object obj = resultSet.getObject(columnName);
                        map.put(columnName, obj);
                    }
                    result.add(map);
                }

                return result;
            }
        });
    }

    /**
     * 获取一些数据库元信息
     *
     * @return
     */
    @GetMapping("/jdbc/meta")
    public Map<String, Object> metaData() {
        Connection connection = null;
        Map<String, Object> map = new HashMap<>();
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            map.put("supportsTransactions", metaData.supportsTransactions());
            map.put("username", metaData.getUserName());
            map.put("url", metaData.getURL());
            map.put("maxConnections", metaData.getMaxConnections());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }


    /**
     * savepoint示例
     *
     * @return
     */
    @GetMapping("/jdbc/savepoint")
    public Map<String, Object> savePoint() {
        Connection connection = null;
        Map<String, Object> map = new HashMap<>();
        Savepoint savepoint = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();

            DatabaseMetaData metaData = connection.getMetaData();
            map.put("supportsTransactions", metaData.supportsTransactions());
            map.put("username", metaData.getUserName());
            map.put("url", metaData.getURL());
            map.put("maxConnections", metaData.getMaxConnections());
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @GetMapping("/user/get")
    public Map<String, Object> getUser(@RequestParam(name = "id", required = false, defaultValue = "1") int id) {
        Map<String, Object> map = new HashMap<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            //不要用Statement，防止sql注入
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id,name,age from user where id = ?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                map.put("id", set.getInt("id"));
                map.put("name", set.getString("name"));
                map.put("age", set.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    //jdk7+onnection实现了AutoCloseable，可不手动关
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return map;
    }

    @PostMapping("/user/add")
    @ResponseBody
    public Map<String, Object> save(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", userService.save(user));
        return map;
    }

    @PostMapping("/user/add2")
    @ResponseBody
    public Map<String, Object> save2(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", userService.save2(user));
        return map;
    }
}
