package com.autocoding.ac.foundation.dao;

import com.autocoding.ac.foundation.constant.OperateConstant;
import com.autocoding.ac.foundation.data.ACPageInfo;
import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACDaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

@Repository
public abstract class IDaoImpl<Data> implements IDao<Data> {
    protected Logger logger = LoggerFactory.getLogger(super.getClass());

    @Autowired
    protected JdbcTemplate defaultJdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected Class<Data> clazz;
    protected RowMapper<Data> rm;
    protected String tableName;
    protected String[] tableKeys;
    static String dbProduct = null;

    @SuppressWarnings("unchecked")
    public IDaoImpl() {
        this.clazz = (Class<Data>) getClassGenricType(super.getClass());
        this.rm = BeanPropertyRowMapper.newInstance(this.clazz);
    }

    private static <T> Class<?> getClassGenricType(Class<?> clazz) {
        return getClassGenricType(clazz, 0);
    }

    @SuppressWarnings("rawtypes")
    private static Class<?> getClassGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if ((index >= params.length) || (index < 0)) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return ((Class) params[index]);
    }

    public boolean save(Data d) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.defaultJdbcTemplate).withTableName(this.tableName);
        try {
            return (insertActor.execute(new BeanPropertySqlParameterSource(d)) > 0);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean batchAdd(List<Data> ds) {
        if ((ds == null) || (ds.size() == 0))
            return true;

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.defaultJdbcTemplate).withTableName(this.tableName);
        SqlParameterSource[] psArray = new SqlParameterSource[ds.size()];
        for (int i = 0; i < ds.size(); ++i)
            psArray[i] = new BeanPropertySqlParameterSource(ds.get(i));
        try {
            int[] result = insertActor.executeBatch(psArray);
            for (int i : result)
                if ((i < 0) && (i != -2))
                    return false;
            return true;
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }


    public boolean update(Data d) {
        StringBuffer sql = new StringBuffer("update " + this.tableName + " set ");

        Field[] fields = d.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            String fieldName = field.getName();
            String dbFieldName = fieldName;
            Class<?> fieldType = field.getType();
            if (fieldName.equals("create_time"))
                continue;
            if (fieldName.equals("creater")) {// 数据库设计保留关键字creater
                continue;
            }
            if ((Arrays.asList(this.tableKeys).contains(dbFieldName)) || (fieldType.equals(Collections.class))
                    || (fieldType.equals(Map.class)) || (fieldType.equals(List.class))
                    || (fieldType.equals(Set.class))) {
                continue;
            }

            sql.append(dbFieldName).append(" = :").append(fieldName).append(", ");
        }
        sql.replace(sql.lastIndexOf(","), sql.length(), "");

        SqlParameterSource ps = new BeanPropertySqlParameterSource(d);
        try {
            this.logger.debug("执行sql为：", sql);
            return (this.namedParameterJdbcTemplate.update(getUpdateWhereCondition(sql), ps) > 0);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean updateKV(Map<String, Object> map, Object[] keys) {
        if ((map == null) || (map.size() == 0))
            return true;
        StringBuffer sql = new StringBuffer("update " + this.tableName + " set ");

        Set<Entry<String, Object>> entrySet = map.entrySet();
        Object[] args = new Object[entrySet.size() + keys.length];
        int j = 0;
        for (Entry<String, Object> entry : entrySet) {
            sql.append(((String) entry.getKey()) + " = ?,");
            args[(j++)] = entry.getValue();
        }
        for (int i = 0; i < keys.length; ++i) {
            args[(i + j)] = keys[i];
        }
        try {
            return (this.defaultJdbcTemplate.update(getWhereKeysCondition(sql.deleteCharAt(sql.length() - 1)),
                    args) > 0);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean batchUpdate(List<Data> ds) {
        if ((ds == null) || (ds.size() == 0))
            return true;
        StringBuffer sql = new StringBuffer("update " + this.tableName + " set ");
        Field[] fields = ds.get(0).getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            if ((Arrays.asList(this.tableKeys).contains(fieldName)) || (fieldType.equals(Collections.class))
                    || (fieldType.equals(Map.class)) || (fieldType.equals(List.class))
                    || (fieldType.equals(Set.class))) {
                continue;
            }

            sql.append(fieldName + " = :" + fieldName + ",");
        }
        sql.replace(sql.lastIndexOf(","), sql.length(), "");

        SqlParameterSource[] psArray = new SqlParameterSource[ds.size()];
        for (int i = 0; i < ds.size(); ++i)
            psArray[i] = new BeanPropertySqlParameterSource(ds.get(i));
        try {
            int[] result = this.namedParameterJdbcTemplate.batchUpdate(getUpdateWhereCondition(sql), psArray);

            for (int i : result)
                if ((i < 0) && (i != -2))
                    return false;
            return true;
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean batchUpdateKV(List<Map<String, Object>> list, List<Object[]> keysList) {
        if ((list == null) || (list.isEmpty()))
            return true;

        StringBuffer sql = null;

        List<Object[]> params = new ArrayList<Object[]>(list.size());
        for (int t = 0; t < list.size(); ++t) {
            Map<String, Object> map = (Map<String, Object>) list.get(t);
            Object[] keys = (Object[]) keysList.get(t);
            sql = new StringBuffer("update " + this.tableName + " set ");

            Set<Entry<String, Object>> entrySet = map.entrySet();
            Object[] args = new Object[entrySet.size() + keys.length];
            int j = 0;
            for (Entry<String, Object> entry : entrySet) {
                sql.append(((String) entry.getKey()) + " = ?,");
                args[(j++)] = entry.getValue();
            }

            for (int i = 0; i < keys.length; ++i) {
                args[(i + j)] = keys[i];
            }
            params.add(args);
        }
        try {
            this.defaultJdbcTemplate.batchUpdate(getWhereKeysCondition(sql.deleteCharAt(sql.length() - 1)), params);
            return true;
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean delete(Object[] keys) {
        StringBuffer sql = new StringBuffer("delete from " + this.tableName);
        try {
            return (this.defaultJdbcTemplate.update(getWhereKeysCondition(sql), keys) > 0);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean batchDelete(final List<Object[]> keys) {
        if ((keys == null) || (keys.size() == 0))
            return true;
        StringBuffer sql = new StringBuffer("delete from " + this.tableName);
        try {
            int[] result = this.defaultJdbcTemplate.batchUpdate(getWhereKeysCondition(sql),
                    new BatchPreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            for (int j = 0; j < keys.size(); j++) {
                                ps.setString(j + 1, keys.get(j).toString());
                            }
                        }

                        @Override
                        public int getBatchSize() {
                            return keys.size();
                        }

                    });
            for (int i : result)
                if ((i < 0) && (i != -2))
                    return false;
            return true;
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public Data getSingle(Object[] keys) {
        try {
            StringBuffer sql = new StringBuffer("select t.* from " + this.tableName + " t");
            List<Data> result = this.defaultJdbcTemplate.query(getWhereKeysCondition(sql), this.rm, keys);
            if (result.size() > 0) {
                return result.get(0);
            }
            return null;
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public boolean isExist(Object[] keys) {
        try {
            StringBuffer sql = new StringBuffer("select count(1) from " + this.tableName + " t");
            int o = ((Integer) this.defaultJdbcTemplate.queryForObject(getWhereKeysCondition(sql), keys, Integer.class))
                    .intValue();

            return (o != 0);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getAll() {
        try {
            String sql = getSql(null, null, false);
            return this.defaultJdbcTemplate.query(sql, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getAll(ACPageInfo page) {
        try {
            page.setTotal(getAllCount());
            String sql = getSql(null, page, false);
            return this.defaultJdbcTemplate.query(sql, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getAll(Map<String, Object> keyMap) {
        try {
            StringBuffer sql = new StringBuffer("select t.* from " + this.tableName + " t");

            return this.namedParameterJdbcTemplate.query(getWhereKeysCondition(sql, keyMap.size()), keyMap, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public int getAllCount() {
        try {
            String sql = getSql(null, null, true);
            return ((Integer) this.defaultJdbcTemplate.queryForObject(sql, Integer.class)).intValue();
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getByCondition(Object[] args) {
        try {
            String sql = getSql(args, null, false);
            return this.defaultJdbcTemplate.query(sql, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getByCondition(ACPageInfo page, Object[] args) {
        try {
            page.setTotal(getByConditionCount(args));
            String sql = getSql(args, page, false);
            return this.defaultJdbcTemplate.query(sql, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    public List<Data> getByCondition(Map<String, Object> conditions) {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ").append(this.tableName);
            Set<String> keys = conditions.keySet();
            int size = keys.size();
            int length = size - 1;
            int index = 0;
            Object[] params = null;
            if (size > 0) {
                sql.append(" where ");
                params = new Object[size];
            }
            for (String key : keys) {
                if (index == length)
                    sql.append(key).append(" = ? ");
                else
                    sql.append(key).append(" = ? and ");
                params[index] = conditions.get(key);
                ++index;
            }

            this.logger.debug(sql.toString());
            if (size == 0) {
                return this.defaultJdbcTemplate.query(sql.toString(), this.rm);
            }
            return this.defaultJdbcTemplate.query(sql.toString(), params, this.rm);
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    private String getWhereKeysCondition(StringBuffer sql, int length) {
        sql.append(" where ");

        for (int index = 0; index < length; ++index) {
            String pk = this.tableKeys[index];
            sql.append(" and " + pk + " = :" + pk);
        }
        sql.replace(sql.indexOf(" and "), sql.indexOf(" and ") + 5, "");

        return sql.toString();
    }

    public int getByConditionCount(Object[] args) {
        try {
            String sql = getSql(args, null, true);
            return ((Integer) this.defaultJdbcTemplate.queryForObject(sql, Integer.class)).intValue();
        } catch (DataAccessException e) {
            this.logger.error("found error", e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }
    }

    protected String getSql(Object[] args, ACPageInfo page, boolean isCount) {
        StringBuffer buffer = new StringBuffer();

        if (isCount)
            buffer.append("select count(1)");
        else
            buffer.append("select t.*").append(getLinkSelect());
        buffer.append(" from ").append(this.tableName).append(" t").append(getLinkTable()).append(" where 1=1")
                .append(getLinkCondition(args));

        if ((args != null) && (args.length > 0))
            buffer.append(generateWhere(args));

        if (page != null)
            buffer = new StringBuffer(getSqlPage(buffer, page));

        if (buffer.toString().indexOf("1=1 and") > 0) {
            this.logger.info("querySql:" + buffer.toString().replace("1=1 and", ""));
            return buffer.toString().replace("1=1 and", "");
        }
        this.logger.info("querySql:" + buffer.toString().replace("where 1=1", ""));
        return buffer.toString().replace("where 1=1", "");
    }

    protected String getLinkSelect() {
        return "";
    }

    protected String getLinkTable() {
        return "";
    }

    protected String getLinkCondition(Object[] args) {
        return "";
    }

    protected abstract String generateWhere(Object[] paramArrayOfObject);

    public StringBuffer getSqlPage(StringBuffer querySql, ACPageInfo page) {
        StringBuffer pageQuerySql = new StringBuffer();
        try {
            if (dbProduct == null) {
                dbProduct = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData()
                        .getDatabaseProductName();
            }
            if (dbProduct.toLowerCase().contains("mysql"))
                pageQuerySql = getMySqlPage(querySql, page);
            else if (dbProduct.toLowerCase().contains("oracle"))
                pageQuerySql = getOraPage(querySql, page);
        } catch (SQLException e) {
            new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        }

        return pageQuerySql;
    }

    private StringBuffer getMySqlPage(StringBuffer querySql, ACPageInfo page) {
        StringBuffer pageQuerySql = new StringBuffer();
        pageQuerySql.append(querySql).append(" limit " + page.getRows() + " offset " + page.getStartRecord());
        return pageQuerySql;
    }

    private StringBuffer getOraPage(StringBuffer querySql, ACPageInfo page) {
        StringBuffer pageQuerySql = new StringBuffer();
        pageQuerySql.append("select * from (select page.*, row_number() over(order by null) as row_number from (")
                .append(querySql).append(" and rownum<=" + (page.getStartRecord() + page.getRows())
                + ") page) where row_number > " + page.getStartRecord());

        return pageQuerySql;
    }

    private String getUpdateWhereCondition(StringBuffer sql) {
        return getKeysCondition(sql, OperateConstant.UPDATE);
    }

    private String getWhereKeysCondition(StringBuffer sql) {
        return getKeysCondition(sql, null);
    }

    private String getKeysCondition(StringBuffer sql, OperateConstant mode) {
        sql.append(" where ");

        if (OperateConstant.UPDATE.equals(mode))
            for (String pk : this.tableKeys)
                sql.append(" and " + pk + " = :" + pk);
        else
            for (String pk : this.tableKeys)
                sql.append(" and " + pk + " = ?");

        sql.replace(sql.indexOf(" and "), sql.indexOf(" and ") + 5, "");

        this.logger.debug(sql.toString());
        return sql.toString();
    }

}
