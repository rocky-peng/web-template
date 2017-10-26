package com.commons.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pengqingsong
 * @date 21/08/2017
 */

public class BaseJdbcDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public long insertAndGetKey(final String sql, final Object... params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    public Map<String, Object> selectOne(String sql, Object... params) {
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, params);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 生成update语句并执行
     *
     * @param tableName     表名
     * @param pkColumnName  主键列的名字
     * @param pkColumnValue 主键列的值
     * @param paramMap      参数key,value对
     * @return 返回受影响的行数
     */
    public int executeUpdateSql(String tableName, String pkColumnName, long pkColumnValue, Map<String, Object> paramMap) {
        List<Object> params = new ArrayList<>(paramMap.size() + 1);
        StringBuilder sql = new StringBuilder("update ").append(tableName).append(" set ");

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            sql.append(entry.getKey()).append("=?,");
            params.add(entry.getKey());
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ").append(pkColumnName).append(" = ? ");
        params.add(pkColumnValue);

        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    /**
     * 生成insert语句并执行
     *
     * @param tableName 表名
     * @param paramMap  参数key,value对
     * @return
     */
    public long insertRecord(String tableName, Map<String, Object> paramMap) {
        List<Object> params = new ArrayList<>(paramMap.size());

        StringBuilder insertInto = new StringBuilder("insert into ").append(tableName).append(" ( ");
        StringBuilder values = new StringBuilder(" ) values ( ");
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            insertInto.append(entry.getKey()).append(", ");
            values.append("?,");
            params.add(entry.getValue());
        }

        insertInto.deleteCharAt(insertInto.length() - 1);  //里面的值是这样的：insert into xxx (a,b,c
        values.deleteCharAt(values.length() - 1);  //里面的值是这样的：  ) values ( ?,?,?
        values.append(" ) "); //里面的值是这样的：  ) values ( ?,?,? )

        insertInto.append(values);  //完整的insert语句:  insert into xxx(a,b,c)values (?,?,?)
        return insertAndGetKey(insertInto.toString(), params.toArray());
    }
}
