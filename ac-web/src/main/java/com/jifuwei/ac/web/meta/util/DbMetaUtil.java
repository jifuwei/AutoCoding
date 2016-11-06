package com.jifuwei.ac.web.meta.util;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbTableMetaInfoData;
import com.jifuwei.ac.web.meta.data.constant.DbMetaInfoConstant;
import com.jifuwei.ac.web.meta.data.po.ACDbMetaInfoPO;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 查询数据库元数据信息工具类
 * Created by JFW on 2016/11/4.
 */
public class DbMetaUtil {
    private static final Logger logger = Logger.getLogger(DbMetaUtil.class);

    private String driverClass;
    private String url;
    private String username;
    private String password;

    //默认使用的数据库连接
    private JdbcTemplate defaultJdbcTemplate = null;

    //获取表注解专用连接
    private Connection connection = null;

    public DbMetaUtil(String driverClass, String url, String username, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            this.defaultJdbcTemplate = new JdbcTemplate(new SimpleDriverDataSource((java.sql.Driver) Class.forName(this.driverClass).newInstance(), this.url, this.username, this.password));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(this.driverClass);
            String url = this.url;
            Properties properties = new Properties();
            properties.setProperty("user", this.username);
            properties.setProperty("password", this.password);
            properties.setProperty("remarks", "true"); //设置可以获取remarks信息
            properties.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            conn = DriverManager.getConnection(url, properties);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JdbcTemplate getDefaultJdbcTemplate() {
        return defaultJdbcTemplate;
    }

    /**
     * 判断数据库是否存在
     * @param db_name
     * @return
     */
    public boolean isExistDataBase(String db_name) {
        try {
            String queryDbName = null;
            Object[] args = {db_name};
            String sql = "SHOW DATABASES LIKE ?";
            queryDbName = defaultJdbcTemplate.queryForObject(sql, args, String.class);
            return db_name.equals(queryDbName) ? true : false;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    /**
     * 获取数据库元数据信息
     *
     * @return
     */
    public ACDbMetaInfoPO findDbMetaInfo() {
        ACDbMetaInfoPO po = null;
        DatabaseMetaData databaseMetaData = null;
        Connection connection = null;
        try {
            connection = this.defaultJdbcTemplate.getDataSource().getConnection();
            databaseMetaData = connection.getMetaData();
            po = new ACDbMetaInfoPO();
            po.setUserName(databaseMetaData.getUserName());
            po.setUrl(databaseMetaData.getURL());
            po.setDatabaseProductName(databaseMetaData.getDatabaseProductName());
            po.setDatabaseProductVersion(databaseMetaData.getDatabaseProductVersion());
            po.setDatabaseDriverName(databaseMetaData.getDriverName());
            po.setDatabaseDriverVersion(databaseMetaData.getDriverVersion());
        } catch (SQLException e) {
            this.logger.debug(e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        } finally {
            closeConnection(connection);
        }

        return po;
    }

    /**
     * dbmd.getTables（String catalog,String schema,String tableName,String[] types）
     * String catalog：要获得表所在的编目。"“”"意味着没有任何编目，Null表示所有编目。
     * String schema：要获得表所在的模式。"“”"意味着没有任何模式，Null表示所有模式。
     * String tableName：指出要返回表名与该参数匹配的那些表，
     * String types：一个指出返回何种表的数组。"TABLE"、"VIEW"、"SYSTEM TABLE"， "GLOBAL TEMPORARY"，"LOCAL  TEMPORARY"，"ALIAS"，"SYSNONYM"。
     * <p/>
     * return 固定返回10个字段描述信息
     */
    public List<ACDbTableMetaInfoData> findDbTablesMetaInfo(String catalog, String schema, String tableName, String[] types) {
        List<ACDbTableMetaInfoData> tableMetaInfoList = new ArrayList<ACDbTableMetaInfoData>();
        ACDbTableMetaInfoData tableMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        Connection conn = null;
        try {
            if (this.connection == null) {
                this.connection = getConnection();
            }
            databaseMetaData = this.connection.getMetaData();
            ResultSet rs = databaseMetaData.getTables(catalog, schema, tableName, types);
            while (rs.next()) {
                tableMetaInfo = new ACDbTableMetaInfoData();
                tableMetaInfo.setTableCat(rs.getString(DbMetaInfoConstant.TABLE_CAT));
                tableMetaInfo.setTableSchem(rs.getString(DbMetaInfoConstant.TABLE_SCHEM));
                tableMetaInfo.setTableName(rs.getString(DbMetaInfoConstant.TABLE_NAME));
                tableMetaInfo.setTableType(rs.getString(DbMetaInfoConstant.TABLE_TYPE));
                tableMetaInfo.setRemarks(rs.getString(DbMetaInfoConstant.REMARKS));
                tableMetaInfo.setTypeCat(rs.getString(DbMetaInfoConstant.TYPE_CAT));
                tableMetaInfo.setTypeSchem(rs.getString(DbMetaInfoConstant.TYPE_SCHEM));
                tableMetaInfo.setTypeName(rs.getString(DbMetaInfoConstant.TYPE_NAME));
                tableMetaInfo.setSelfReferencingColName(rs.getString(DbMetaInfoConstant.SELF_REFERENCING_COL_NAME));
                tableMetaInfo.setRefGeneration(rs.getString(DbMetaInfoConstant.REF_GENERATION));
                tableMetaInfoList.add(tableMetaInfo);
            }

            if (tableMetaInfoList.size() > 0) {
                return tableMetaInfoList;
            }
        } catch (SQLException e) {
            this.logger.debug(e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        } finally {
            closeConnection(conn);
        }
        return null;
    }

    /**
     * dbmd.getColumns(String catalog,String schama,String tablename,String columnNamePattern)
     * String catalog：要获得表所在的编目。"“”"意味着没有任何编目，Null表示所有编目。
     * String schema：要获得表所在的模式。"“”"意味着没有任何模式，Null表示所有模式。
     * String tableName：指出要返回表名与该参数匹配的那些表，
     * String columnNamePattern：列名称模式；它必须与存储在数据库中的列名称匹配
     * <p/>
     * return 固定返回23个字段描述信息
     */
    public List<ACDbColumnMetaInfoData> findDbColumnsMetaInfo(String catalog, String schema, String tablename, String columnNamePattern) {
        List<ACDbColumnMetaInfoData> columnMetaInfoList = new ArrayList<ACDbColumnMetaInfoData>();
        ACDbColumnMetaInfoData columnMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        Connection connection = null;
        try {
            connection = this.defaultJdbcTemplate.getDataSource().getConnection();
            databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getColumns(catalog, schema, tablename, columnNamePattern);
            while (rs.next()) {
                columnMetaInfo = new ACDbColumnMetaInfoData();
                columnMetaInfo.setTableCat(DbMetaInfoConstant.TABLE_CAT);
                columnMetaInfo.setTableSchem(rs.getString(DbMetaInfoConstant.TABLE_SCHEM));
                columnMetaInfo.setTableName(rs.getString(DbMetaInfoConstant.TABLE_NAME));
                columnMetaInfo.setColumnName(rs.getString(DbMetaInfoConstant.COLUMN_NAME));
                columnMetaInfo.setDataType(rs.getInt(DbMetaInfoConstant.DATA_TYPE));
                columnMetaInfo.setTypeName(rs.getString(DbMetaInfoConstant.TYPE_NAME));
                columnMetaInfo.setColumnSize(rs.getInt(DbMetaInfoConstant.COLUMN_SIZE));
                columnMetaInfo.setBufferLength(rs.getString(DbMetaInfoConstant.BUFFER_LENGTH));
                columnMetaInfo.setDecimalDigits(rs.getInt(DbMetaInfoConstant.DECIMAL_DIGITS));
                columnMetaInfo.setNumPrecRadix(rs.getInt(DbMetaInfoConstant.NUM_PREC_RADIX));
                columnMetaInfo.setNullable(rs.getInt(DbMetaInfoConstant.NULLABLE));
                columnMetaInfo.setRemarks(rs.getString(DbMetaInfoConstant.REMARKS));
                columnMetaInfo.setColumnDef(rs.getString(DbMetaInfoConstant.COLUMN_DEF));
                columnMetaInfo.setSqlDataType(rs.getInt(DbMetaInfoConstant.SQL_DATA_TYPE));
                columnMetaInfo.setSqlDatetimeSub(rs.getInt(DbMetaInfoConstant.SQL_DATETIME_SUB));
                columnMetaInfo.setCharOctetLength(rs.getInt(DbMetaInfoConstant.CHAR_OCTET_LENGTH));
                columnMetaInfo.setOrdinalPosition(rs.getInt(DbMetaInfoConstant.ORDINAL_POSITION));
                columnMetaInfo.setIsNullable(rs.getString(DbMetaInfoConstant.IS_NULLABLE));
                //columnMetaInfo.setScopeCatlog(rs.getString(DbMetaInfoConstant.SCOPE_CATLOG));//无法获取
                columnMetaInfo.setScopeSchema(rs.getString(DbMetaInfoConstant.SCOPE_SCHEMA));
                columnMetaInfo.setScopeTable(rs.getString(DbMetaInfoConstant.SCOPE_TABLE));
                columnMetaInfo.setSourceDataType(rs.getShort(DbMetaInfoConstant.SOURCE_DATA_TYPE));
                columnMetaInfo.setIsAutoincrement(rs.getString(DbMetaInfoConstant.IS_AUTOINCREMENT));
                columnMetaInfoList.add(columnMetaInfo);
            }

            if (columnMetaInfoList.size() > 0) {
                return columnMetaInfoList;
            }
        } catch (SQLException e) {
            this.logger.debug(e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    /**
     * dbmd.getPrimaryKeys(String catalog, String schema, String tableName)
     * String catalog：要获得表所在的编目。"“”"意味着没有任何编目，Null表示所有编目。
     * String schema：要获得表所在的模式。"“”"意味着没有任何模式，Null表示所有模式。
     * String tableName：指出要返回表名与该参数匹配的那些表，
     * <p/>
     * return 固定返回6个字段描述信息
     */
    public List<ACDbPrimaryKeyMetaInfoData> findDbPrimaryKeysMetaInfo(String catalog, String schema, String tableName) {
        List<ACDbPrimaryKeyMetaInfoData> primaryKeyMetaInfoList = new ArrayList<ACDbPrimaryKeyMetaInfoData>();
        ACDbPrimaryKeyMetaInfoData primaryKeyMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        Connection connection = null;
        try {
            connection = this.defaultJdbcTemplate.getDataSource().getConnection();
            databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            while (rs.next()) {
                primaryKeyMetaInfo = new ACDbPrimaryKeyMetaInfoData();
                primaryKeyMetaInfo.setTableCat(rs.getString(DbMetaInfoConstant.TABLE_CAT));
                primaryKeyMetaInfo.setTableSchem(rs.getString(DbMetaInfoConstant.TABLE_SCHEM));
                primaryKeyMetaInfo.setTableName(rs.getString(DbMetaInfoConstant.TABLE_NAME));
                primaryKeyMetaInfo.setColumnName(rs.getString(DbMetaInfoConstant.COLUMN_NAME));
                primaryKeyMetaInfo.setKeySeq(rs.getString(DbMetaInfoConstant.KEY_SEQ));
                primaryKeyMetaInfo.setPkName(rs.getString(DbMetaInfoConstant.PK_NAME));
                primaryKeyMetaInfoList.add(primaryKeyMetaInfo);
            }

            if (primaryKeyMetaInfoList.size() > 0) {
                return primaryKeyMetaInfoList;
            }
        } catch (SQLException e) {
            this.logger.debug(e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    /**
     * dbmd.getExportedKeys(String catalog, String schema, String tableName)
     * String catalog：要获得表所在的编目。"“”"意味着没有任何编目，Null表示所有编目。
     * String schema：要获得表所在的模式。"“”"意味着没有任何模式，Null表示所有模式。
     * String tableName：指出要返回表名与该参数匹配的那些表，
     * <p/>
     * return 固定返回6个字段描述信息
     */
    public List<ACDbExportedKeyMetaInfoData> findDbExportedKeysMetaInfo(String catalog, String schema, String tableName) {
        List<ACDbExportedKeyMetaInfoData> exportedKeyMetaInfoList = new ArrayList<ACDbExportedKeyMetaInfoData>();
        ACDbExportedKeyMetaInfoData exportedKeyMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        Connection connection = null;
        try {
            connection = this.defaultJdbcTemplate.getDataSource().getConnection();
            databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getExportedKeys(catalog, schema, tableName);
            while (rs.next()) {
                exportedKeyMetaInfo = new ACDbExportedKeyMetaInfoData();
                exportedKeyMetaInfo.setPktableCat(rs.getString(DbMetaInfoConstant.PKTABLE_CAT));
                exportedKeyMetaInfo.setPktableSchem(rs.getString(DbMetaInfoConstant.PKTABLE_SCHEM));
                exportedKeyMetaInfo.setPktableName(rs.getString(DbMetaInfoConstant.PKTABLE_NAME));
                exportedKeyMetaInfo.setPkcolumnName(rs.getString(DbMetaInfoConstant.PKCOLUMN_NAME));
                exportedKeyMetaInfo.setFktableCat(rs.getString(DbMetaInfoConstant.FKTABLE_CAT));
                exportedKeyMetaInfo.setFktableSchem(rs.getString(DbMetaInfoConstant.FKTABLE_SCHEM));
                exportedKeyMetaInfo.setFktableName(rs.getString(DbMetaInfoConstant.FKTABLE_NAME));
                exportedKeyMetaInfo.setFkcolumnName(rs.getString(DbMetaInfoConstant.FKCOLUMN_NAME));
                exportedKeyMetaInfo.setKeySeq(rs.getShort(DbMetaInfoConstant.KEY_SEQ));
                exportedKeyMetaInfo.setUpdateRule(rs.getShort(DbMetaInfoConstant.UPDATE_RULE));
                exportedKeyMetaInfo.setDeleteRule(rs.getShort(DbMetaInfoConstant.DELETE_RULE));
                exportedKeyMetaInfo.setPkName(rs.getString(DbMetaInfoConstant.PK_NAME));
                exportedKeyMetaInfo.setFkName(rs.getString(DbMetaInfoConstant.FK_NAME));
                exportedKeyMetaInfo.setDeferrability(rs.getShort(DbMetaInfoConstant.DEFERRABILITY));
                exportedKeyMetaInfoList.add(exportedKeyMetaInfo);
            }

            if (exportedKeyMetaInfoList.size() > 0) {
                return exportedKeyMetaInfoList;
            }
        } catch (SQLException e) {
            this.logger.debug(e);
            throw new ACDaoException(ACErrorMsg.ERROR_DATABASE_TECH_EXCEPTION);
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    /**
     * 创建数据库
     * @param db_name
     */
    public void createDatabase(String db_name) {
        String sql = "CREATE DATABASE " + db_name;//TODO:有sql注入危险，使用参数总是报语句错误，未能找到错误原因20161103
        this.defaultJdbcTemplate.update(sql);
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
