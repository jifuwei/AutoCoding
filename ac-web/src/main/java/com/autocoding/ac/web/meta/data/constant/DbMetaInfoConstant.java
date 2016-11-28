package com.autocoding.ac.web.meta.data.constant;

/**
 * 数据库元数据信息常量字段
 * Created by JFW on 2016/10/11.
 */
public final class DbMetaInfoConstant {
    public static final String TABLE_CAT = "TABLE_CAT"; //表类别
    public static final String TABLE_SCHEM = "TABLE_SCHEM"; //表模式
    public static final String TABLE_NAME = "TABLE_NAME"; //表名称
    public static final String TABLE_TYPE = "TABLE_TYPE"; //表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"
    public static final String COLUMN_NAME = "COLUMN_NAME"; //列名称
    public static final String DATA_TYPE = "DATA_TYPE"; //来自 java.sql.Types 的 SQL 类型
    public static final String TYPE_NAME = "TYPE_NAME"; //数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
    public static final String COLUMN_SIZE = "COLUMN_SIZE"; //列的大小
    public static final String BUFFER_LENGTH = "BUFFER_LENGTH"; //未被使用
    public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS"; //小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null
    public static final String NUM_PREC_RADIX = "NUM_PREC_RADIX"; //基数（通常为 10 或 2）
    public static final String NULLABLE = "NULLABLE"; //是否允许使用 NULL
    public static final String REMARKS = "REMARKS"; //描述列的注释
    public static final String COLUMN_DEF = "COLUMN_DEF"; //该列的默认值，当值在单引号内时应被解释为一个字符串
    public static final String SQL_DATA_TYPE = "SQL_DATA_TYPE"; //未使用
    public static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB"; //未使用
    public static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH"; //对于 char 类型，该长度是列中的最大字节数
    public static final String ORDINAL_POSITION = "ORDINAL_POSITION"; //表中的列的索引（从 1 开始）
    public static final String IS_NULLABLE = "IS_NULLABLE"; //ISO 规则用于确定列是否包括 null
    public static final String SCOPE_CATLOG = "SCOPE_CATLOG"; //表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
    public static final String SCOPE_SCHEMA = "SCOPE_SCHEMA"; //表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
    public static final String SCOPE_TABLE = "SCOPE_TABLE"; //表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
    public static final String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE"; //不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null）
    public static final String IS_AUTOINCREMENT = "IS_AUTOINCREMENT"; //是否自动增加
    public static final String TYPE_CAT = "TYPE_CAT"; //类型的类别
    public static final String TYPE_SCHEM = "TYPE_SCHEM"; //类型模式
    public static final String SELF_REFERENCING_COL_NAME = "SELF_REFERENCING_COL_NAME"; //有类型表的指定 "identifier" 列的名称
    public static final String REF_GENERATION = "REF_GENERATION"; //指定在 SELF_REFERENCING_COL_NAME 中创建值的方式。这些值为 "SYSTEM"、"USER" 和 "DERIVED"
    public static final String KEY_SEQ = "KEY_SEQ"; //主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）。
    public static final String PK_NAME = "PK_NAME"; //主键的名称
    public static final String PKTABLE_CAT = "PKTABLE_CAT"; //被导入的主键表类别
    public static final String PKTABLE_SCHEM = "PKTABLE_SCHEM"; //被导入的主键表模式
    public static final String PKTABLE_NAME = "PKTABLE_NAME"; //被导入的主键表名称
    public static final String PKCOLUMN_NAME = "PKCOLUMN_NAME"; //被导入的主键列名称
    public static final String FKTABLE_CAT = "FKTABLE_CAT"; //外键表类别
    public static final String FKTABLE_SCHEM = "FKTABLE_SCHEM"; //外键表模式
    public static final String FKTABLE_NAME = "FKTABLE_NAME"; //外键表名称
    public static final String FKCOLUMN_NAME = "FKCOLUMN_NAME"; //外键列名称
    public static final String UPDATE_RULE = "UPDATE_RULE"; //更新主键时外键发生的变化
    public static final String DELETE_RULE = "DELETE_RULE"; //删除主键时外键发生的变化
    public static final String FK_NAME = "FK_NAME"; //外键的名称
    public static final String DEFERRABILITY = "DEFERRABILITY"; //是否可以将对外键约束的评估延迟到提交时间
}
