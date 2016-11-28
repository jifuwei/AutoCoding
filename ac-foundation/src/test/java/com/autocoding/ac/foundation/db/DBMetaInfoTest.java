package com.autocoding.ac.foundation.db;

import com.autocoding.ac.foundation.base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 该类用于测试抓取数据库元数据所用
 * Created by JFW on 2016/10/5.
 */
public class DBMetaInfoTest extends BaseTest {
//    private static final Logger logger = LoggerFactory.getLogger(DBMetaInfoTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate = null;

    private DatabaseMetaData databaseMetaData = null;

    @Before
    public void before() throws SQLException {
        if (databaseMetaData == null)
            databaseMetaData = this.jdbcTemplate.getDataSource().getConnection().getMetaData();
    }

    /**
     * 获取数据库相关信息
     *
     * @throws SQLException
     */
    @Test
    public void getDataBaseInfoTest() throws SQLException {
        System.out.println("数据库已知的用户: " + databaseMetaData.getUserName());
        System.out.println("数据库的系统函数的逗号分隔列表: " + databaseMetaData.getSystemFunctions());
        System.out.println("数据库的时间和日期函数的逗号分隔列表: " + databaseMetaData.getTimeDateFunctions());
        System.out.println("数据库的字符串函数的逗号分隔列表: " + databaseMetaData.getStringFunctions());
        System.out.println("数据库供应商用于 'schema' 的首选术语: " + databaseMetaData.getSchemaTerm());
        System.out.println("数据库URL: " + databaseMetaData.getURL());
        System.out.println("是否允许只读:" + databaseMetaData.isReadOnly());
        System.out.println("数据库的产品名称:" + databaseMetaData.getDatabaseProductName());
        System.out.println("数据库的版本:" + databaseMetaData.getDatabaseProductVersion());
        System.out.println("驱动程序的名称:" + databaseMetaData.getDriverName());
        System.out.println("驱动程序的版本:" + databaseMetaData.getDriverVersion());

        System.out.println();
        System.out.println("数据库中使用的表类型");
        ResultSet rs = databaseMetaData.getTableTypes();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
    }

    /**
     * 获取当前用户下所有表
     *
     * @throws SQLException
     */
    @Test
    public void getAllTableListTest() throws SQLException {
        // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE",
        // "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
        String[] types = {"TABLE"};
        ResultSet rs = databaseMetaData.getTables(null, null, "%", types);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME"); // 表名
            String tableType = rs.getString("TABLE_TYPE"); // 表类型
            String remarks = rs.getString("REMARKS"); // 表备注
            System.out.println(tableName + "-" + tableType + "-" + remarks);
        }
    }

    /**
     * 获得该用户下面的所有视图
     *
     * @throws SQLException
     */
    @Test
    public void getAllViewListTest() throws SQLException {
        // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE",
        // "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
        String[] types = {"VIEW"};
        ResultSet rs = databaseMetaData.getTables(null, null, "%", types);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME"); // 表名
            String tableType = rs.getString("TABLE_TYPE"); // 表类型
            String remarks = rs.getString("REMARKS"); // 表备注
            System.out.println(tableName + "-" + tableType + "-" + remarks);
        }
    }

    /**
     * 获得数据库中所有方案名称
     *
     * @throws SQLException
     */
    @Test
    public void getAllSchemasTest() throws SQLException {
        ResultSet rs = databaseMetaData.getSchemas();
        while (rs.next()) {
            String tableSchem = rs.getString("TABLE_SCHEM");
            System.out.println(tableSchem);
        }
    }

    /**
     * 获得表或视图中的所有列信息
     *
     * @throws SQLException
     */
    @Test
    public void getTableColumnsTest() throws SQLException {
        ResultSet rs = databaseMetaData.getColumns(null, null, "cip_admin_text", "%");
        while (rs.next()) {
            String tableCat = rs.getString("TABLE_CAT");// 表目录（可能为空）
            String tableSchemaName = rs.getString("TABLE_SCHEM");// 表的架构（可能为空）
            String tableName_ = rs.getString("TABLE_NAME");// 表名
            String columnName = rs.getString("COLUMN_NAME");// 列名
            int dataType = rs.getInt("DATA_TYPE"); // 对应的java.sql.Types类型
            String dataTypeName = rs.getString("TYPE_NAME");// java.sql.Types类型
            // 名称
            int columnSize = rs.getInt("COLUMN_SIZE");// 列大小
            int decimalDigits = rs.getInt("DECIMAL_DIGITS");// 小数位数
            int numPrecRadix = rs.getInt("NUM_PREC_RADIX");// 基数（通常是10或2）
            int nullAble = rs.getInt("NULLABLE");// 是否允许为null
            String remarks = rs.getString("REMARKS");// 列描述
            String columnDef = rs.getString("COLUMN_DEF");// 默认值
            int sqlDataType = rs.getInt("SQL_DATA_TYPE");// sql数据类型
            int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB"); // SQL日期时间分?
            int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH"); // char类型的列中的最大字节数
            int ordinalPosition = rs.getInt("ORDINAL_POSITION"); // 表中列的索引（从1开始）

            /**
             * ISO规则用来确定某一列的为空性。 是---如果该参数可以包括空值; 无---如果参数不能包含空值
             * 空字符串---如果参数为空性是未知的
             */
            String isNullAble = rs.getString("IS_NULLABLE");

            /**
             * 指示此列是否是自动递增 是---如果该列是自动递增 无---如果不是自动递增列 空字串---如果不能确定它是否
             * 列是自动递增的参数是未知
             */
            String isAutoincrement = rs.getString("IS_AUTOINCREMENT");

            System.out.println(tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"
                    + dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix
                    + "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub
                    + charOctetLength + "-" + ordinalPosition + "-" + isNullAble + "-" + isAutoincrement + "-");
        }
    }

    /**
     * 获得一个表的主键信息
     *
     * @throws SQLException
     */
    @Test
    public void getAllPrimaryKeysTest() throws SQLException {
        ResultSet rs = databaseMetaData.getPrimaryKeys(null, null, "cip_admin_text");
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");// 列名
            short keySeq = rs.getShort("KEY_SEQ");// 序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
            String pkName = rs.getString("PK_NAME"); // 主键名称
            System.out.println(columnName + "-" + keySeq + "-" + pkName);
        }
    }

    /**
     * 获得一个表的索引信息
     *
     * @throws SQLException
     */
    @Test
    public void getIndexInfoTest() throws SQLException {
        ResultSet rs = databaseMetaData.getIndexInfo(null, null, "cip_admin_text", true, true);
        while (rs.next()) {
            boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引(Can
            // index values
            // be
            // non-unique.
            // false when
            // TYPE is
            // tableIndexStatistic
            // )
            String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
            String indexName = rs.getString("INDEX_NAME");// 索引的名称
            short type = rs.getShort("TYPE");// 索引类型
            short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
            String columnName = rs.getString("COLUMN_NAME");// 列名
            String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
            int cardinality = rs.getInt("CARDINALITY"); // 基数
            System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-"
                    + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
        }
    }

    /**
     * 获得一个表的外键信息
     *
     * @throws SQLException
     */
    @Test
    public void getAllExportedKeysTest() throws SQLException {
        ResultSet rs = databaseMetaData.getExportedKeys(null, null, "cip_admin_text");
        while (rs.next()) {
            String pkTableCat = rs.getString("PKTABLE_CAT");// 主键表的目录（可能为空）
            String pkTableSchem = rs.getString("PKTABLE_SCHEM");// 主键表的架构（可能为空）
            String pkTableName = rs.getString("PKTABLE_NAME");// 主键表名
            String pkColumnName = rs.getString("PKCOLUMN_NAME");// 主键列名
            String fkTableCat = rs.getString("FKTABLE_CAT");// 外键的表的目录（可能为空）出口（可能为null）
            String fkTableSchem = rs.getString("FKTABLE_SCHEM");// 外键表的架构（可能为空）出口（可能为空）
            String fkTableName = rs.getString("FKTABLE_NAME");// 外键表名
            String fkColumnName = rs.getString("FKCOLUMN_NAME"); // 外键列名
            short keySeq = rs.getShort("KEY_SEQ");// 序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。

            /**
             * hat happens to foreign key when primary is updated:
             * importedNoAction - do not allow update of primary key if it
             * has been imported importedKeyCascade - change imported key to
             * agree with primary key update importedKeySetNull - change
             * imported key to NULL if its primary key has been updated
             * importedKeySetDefault - change imported key to default values
             * if its primary key has been updated importedKeyRestrict -
             * same as importedKeyNoAction (for ODBC 2.x compatibility)
             */
            short updateRule = rs.getShort("UPDATE_RULE");

            /**
             * What happens to the foreign key when primary is deleted.
             * importedKeyNoAction - do not allow delete of primary key if
             * it has been imported importedKeyCascade - delete rows that
             * import a deleted key importedKeySetNull - change imported key
             * to NULL if its primary key has been deleted
             * importedKeyRestrict - same as importedKeyNoAction (for ODBC
             * 2.x compatibility) importedKeySetDefault - change imported
             * key to default if its primary key has been deleted
             */
            short delRule = rs.getShort("DELETE_RULE");
            String fkName = rs.getString("FK_NAME");// 外键的名称（可能为空）
            String pkName = rs.getString("PK_NAME");// 主键的名称（可能为空）

            /**
             * can the evaluation of foreign key constraints be deferred
             * until commit importedKeyInitiallyDeferred - see SQL92 for
             * definition importedKeyInitiallyImmediate - see SQL92 for
             * definition importedKeyNotDeferrable - see SQL92 for
             * definition
             */
            short deferRability = rs.getShort("DEFERRABILITY");

            System.out.println(pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-"
                    + fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-"
                    + updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);
        }
    }

}
