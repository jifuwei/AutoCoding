package com.jifuwei.ac.web.meta.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACDaoException;
import com.jifuwei.ac.web.meta.dao.ACDbMetaInfoDao;
import com.jifuwei.ac.web.meta.data.constant.DbMetaInfoConstant;
import com.jifuwei.ac.web.meta.data.po.*;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 抓取数据库连接信息实现类
 * Created by JFW on 2016/10/11.
 */
@Repository("ACDbMetaInfoDaoImpl")
public class ACDbMetaInfoDaoImpl extends IDaoImpl<ACDbMetaInfoPO> implements ACDbMetaInfoDao {

    public ACDbMetaInfoDaoImpl() {
        super();
        this.tableName = "";
        this.tableKeys = new String[]{};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }

    @Override
    public ACDbMetaInfoPO findDbMetaInfo() {
        ACDbMetaInfoPO po = null;
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData();
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
        }
        return po;
    }

    @Override
    public List<ACDbTableMetaInfoPO> findDbTablesMetaInfo(String catalog, String schema, String tableName, String[] types) {
        List<ACDbTableMetaInfoPO> tableMetaInfoList = new ArrayList<ACDbTableMetaInfoPO>();
        ACDbTableMetaInfoPO tableMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet rs = databaseMetaData.getTables(catalog, schema, tableName, types);
            while (rs.next()) {
                tableMetaInfo = new ACDbTableMetaInfoPO();
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
        }
        return null;
    }

    @Override
    public List<ACDbColumnMetaInfoPO> findDbColumnsMetaInfo(String catalog, String schema, String tablename, String columnNamePattern) {
        List<ACDbColumnMetaInfoPO> columnMetaInfoList = new ArrayList<ACDbColumnMetaInfoPO>();
        ACDbColumnMetaInfoPO columnMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet rs = databaseMetaData.getColumns(catalog, schema, tablename, columnNamePattern);
            while (rs.next()) {
                columnMetaInfo = new ACDbColumnMetaInfoPO();
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
                //columnMetaInfo.setScopeCatlog(rs.getString(DbMetaInfoConstant.SCOPE_CATLOG));
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
        }
        return null;
    }

    @Override
    public List<ACDbPrimaryKeyMetaInfoPO> findDbPrimaryKeysMetaInfo(String catalog, String schema, String tableName) {
        List<ACDbPrimaryKeyMetaInfoPO> primaryKeyMetaInfoList = new ArrayList<ACDbPrimaryKeyMetaInfoPO>();
        ACDbPrimaryKeyMetaInfoPO primaryKeyMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet rs = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            while (rs.next()) {
                primaryKeyMetaInfo = new ACDbPrimaryKeyMetaInfoPO();
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
        }
        return null;
    }

    @Override
    public List<ACDbExportedKeyMetaInfoPO> findDbExportedKeysMetaInfo(String catalog, String schema, String tableName) {
        List<ACDbExportedKeyMetaInfoPO> exportedKeyMetaInfoList = new ArrayList<ACDbExportedKeyMetaInfoPO>();
        ACDbExportedKeyMetaInfoPO exportedKeyMetaInfo = null;
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = this.defaultJdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet rs = databaseMetaData.getExportedKeys(catalog, schema, tableName);
            while (rs.next()) {
                exportedKeyMetaInfo = new ACDbExportedKeyMetaInfoPO();
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
        }
        return null;
    }
}
