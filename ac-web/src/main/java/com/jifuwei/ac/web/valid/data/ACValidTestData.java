package com.jifuwei.ac.web.valid.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 测试Data
 * Created by JFW on 2016/10/6.
 */
public class ACValidTestData {
    private String mysql_varchar;       //字符串：可变长度字符，最后一位标识长度
    private String mysql_char;          //字符：固定长度的字符
    private String mysql_blob;          //二进制大对象:一般用于存储文件字符流等信息,String代表文件名
    private String mysql_text;          //文本：存储超大字符串，一般用于存储富文本信息，如html标签等
    private Integer mysql_int;          //整形：存储大小为：4字节，范围：-2^31 ~ 2^31-1
    private Integer mysql_tinyint;      //小整形：存储大小：1字节，范围：0 ~255
    private Integer mysql_smallint;     //短整形：存储大小：2字节，范围：-2^15 ~ 2^15-1
    private Integer mysql_mediumint;    //中整形：存储大小：3字节
    private BigInteger mysql_bigint;    //大整形：存储大小：8字节，范围：-2^63 ~ 2^63-1
    private boolean mysql_bit;          //比特：二进制位存储，只能存储1或0
    private Float mysql_float;          //浮点型：4字节
    private Double mysql_double;        //双精度型：8字节
    private BigDecimal mysql_decimal;   //小数
    private Integer mysql_boolean;      //布尔型：mysql默认转成tinyint(1),false为0，true为1
    private Date mysql_date;            //日期：YYYY-MM-DD
    private Time mysql_time;            //时间：HH: MM:SS
    private Timestamp mysql_datetime;   //日期时间：YYYY-MM-DD HH: MM:SS
    private Timestamp mysql_timestamp;  //时间戳：YYYYMMDD HHMMSS
    private Date mysql_year;            //年：YYYY

    public String getMysql_varchar() {
        return mysql_varchar;
    }

    public void setMysql_varchar(String mysql_varchar) {
        this.mysql_varchar = mysql_varchar;
    }

    public String getMysql_char() {
        return mysql_char;
    }

    public void setMysql_char(String mysql_char) {
        this.mysql_char = mysql_char;
    }

    public String getMysql_blob() {
        return mysql_blob;
    }

    public void setMysql_blob(String mysql_blob) {
        this.mysql_blob = mysql_blob;
    }

    public String getMysql_text() {
        return mysql_text;
    }

    public void setMysql_text(String mysql_text) {
        this.mysql_text = mysql_text;
    }

    public Integer getMysql_int() {
        return mysql_int;
    }

    public void setMysql_int(Integer mysql_int) {
        this.mysql_int = mysql_int;
    }

    public Integer getMysql_tinyint() {
        return mysql_tinyint;
    }

    public void setMysql_tinyint(Integer mysql_tinyint) {
        this.mysql_tinyint = mysql_tinyint;
    }

    public Integer getMysql_smallint() {
        return mysql_smallint;
    }

    public void setMysql_smallint(Integer mysql_smallint) {
        this.mysql_smallint = mysql_smallint;
    }

    public Integer getMysql_mediumint() {
        return mysql_mediumint;
    }

    public void setMysql_mediumint(Integer mysql_mediumint) {
        this.mysql_mediumint = mysql_mediumint;
    }

    public BigInteger getMysql_bigint() {
        return mysql_bigint;
    }

    public void setMysql_bigint(BigInteger mysql_bigint) {
        this.mysql_bigint = mysql_bigint;
    }

    public boolean isMysql_bit() {
        return mysql_bit;
    }

    public void setMysql_bit(boolean mysql_bit) {
        this.mysql_bit = mysql_bit;
    }

    public Float getMysql_float() {
        return mysql_float;
    }

    public void setMysql_float(Float mysql_float) {
        this.mysql_float = mysql_float;
    }

    public Double getMysql_double() {
        return mysql_double;
    }

    public void setMysql_double(Double mysql_double) {
        this.mysql_double = mysql_double;
    }

    public BigDecimal getMysql_decimal() {
        return mysql_decimal;
    }

    public void setMysql_decimal(BigDecimal mysql_decimal) {
        this.mysql_decimal = mysql_decimal;
    }

    public Integer getMysql_boolean() {
        return mysql_boolean;
    }

    public void setMysql_boolean(Integer mysql_boolean) {
        this.mysql_boolean = mysql_boolean;
    }

    public Date getMysql_date() {
        return mysql_date;
    }

    public void setMysql_date(Date mysql_date) {
        this.mysql_date = mysql_date;
    }

    public Time getMysql_time() {
        return mysql_time;
    }

    public void setMysql_time(Time mysql_time) {
        this.mysql_time = mysql_time;
    }

    public Timestamp getMysql_datetime() {
        return mysql_datetime;
    }

    public void setMysql_datetime(Timestamp mysql_datetime) {
        this.mysql_datetime = mysql_datetime;
    }

    public Timestamp getMysql_timestamp() {
        return mysql_timestamp;
    }

    public void setMysql_timestamp(Timestamp mysql_timestamp) {
        this.mysql_timestamp = mysql_timestamp;
    }

    public Date getMysql_year() {
        return mysql_year;
    }

    public void setMysql_year(Date mysql_year) {
        this.mysql_year = mysql_year;
    }
}
