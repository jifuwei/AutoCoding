package com.autocoding.ac.web.valid.data.po;

import com.autocoding.ac.web.valid.data.vo.ACValidTestVO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 测试PO
 * Created by JFW on 2016/10/6.
 */
public class ACValidTestPO {
    private Integer acId;              //主键
    private String mysqlVarchar;       //字符串：可变长度字符，最后一位标识长度
    private String mysqlChar;          //字符：固定长度的字符
    private byte[] mysqlBlob;          //二进制大对象:一般用于存储文件字符流等信息,String代表文件名
    private String mysqlText;          //文本：存储超大字符串，一般用于存储富文本信息，如html标签等
    private Integer mysqlInt;          //整形：存储大小为：4字节，范围：-2^31 ~ 2^31-1
    private Integer mysqlTinyint;      //小整形：存储大小：1字节，范围：0 ~255
    private Integer mysqlSmallint;     //短整形：存储大小：2字节，范围：-2^15 ~ 2^15-1
    private Integer mysqlMediumint;    //中整形：存储大小：3字节
    private BigInteger mysqlBigint;    //大整形：存储大小：8字节，范围：-2^63 ~ 2^63-1
    private Boolean mysqlBit;          //比特：二进制位存储，只能存储1或0
    private Float mysqlFloat;          //浮点型：4字节
    private Double mysqlDouble;        //双精度型：8字节
    private BigDecimal mysqlDecimal;   //小数
    private Boolean mysqlBoolean;      //布尔型：mysql默认转成tinyint(1),false为0，true为1
    private Date mysqlDate;            //日期：YYYY-MM-DD
    private Time mysqlTime;            //时间：HH: MM:SS
    private Timestamp mysqlDatetime;   //日期时间：YYYY-MM-DD HH: MM:SS
    private Timestamp mysqlTimestamp;  //时间戳：YYYYMMDD HHMMSS
    private String mysqlYear;            //年：YYYY

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getMysqlVarchar() {
        return mysqlVarchar;
    }

    public void setMysqlVarchar(String mysqlVarchar) {
        this.mysqlVarchar = mysqlVarchar;
    }

    public String getMysqlChar() {
        return mysqlChar;
    }

    public void setMysqlChar(String mysqlChar) {
        this.mysqlChar = mysqlChar;
    }

    public byte[] getMysqlBlob() {
        return mysqlBlob;
    }

    public void setMysqlBlob(byte[] mysqlBlob) {
        this.mysqlBlob = mysqlBlob;
    }

    public String getMysqlText() {
        return mysqlText;
    }

    public void setMysqlText(String mysqlText) {
        this.mysqlText = mysqlText;
    }

    public Integer getMysqlInt() {
        return mysqlInt;
    }

    public void setMysqlInt(Integer mysqlInt) {
        this.mysqlInt = mysqlInt;
    }

    public Integer getMysqlTinyint() {
        return mysqlTinyint;
    }

    public void setMysqlTinyint(Integer mysqlTinyint) {
        this.mysqlTinyint = mysqlTinyint;
    }

    public Integer getMysqlSmallint() {
        return mysqlSmallint;
    }

    public void setMysqlSmallint(Integer mysqlSmallint) {
        this.mysqlSmallint = mysqlSmallint;
    }

    public Integer getMysqlMediumint() {
        return mysqlMediumint;
    }

    public void setMysqlMediumint(Integer mysqlMediumint) {
        this.mysqlMediumint = mysqlMediumint;
    }

    public BigInteger getMysqlBigint() {
        return mysqlBigint;
    }

    public void setMysqlBigint(BigInteger mysqlBigint) {
        this.mysqlBigint = mysqlBigint;
    }

    public Boolean getMysqlBit() {
        return mysqlBit;
    }

    public void setMysqlBit(Boolean mysqlBit) {
        this.mysqlBit = mysqlBit;
    }

    public Float getMysqlFloat() {
        return mysqlFloat;
    }

    public void setMysqlFloat(Float mysqlFloat) {
        this.mysqlFloat = mysqlFloat;
    }

    public Double getMysqlDouble() {
        return mysqlDouble;
    }

    public void setMysqlDouble(Double mysqlDouble) {
        this.mysqlDouble = mysqlDouble;
    }

    public BigDecimal getMysqlDecimal() {
        return mysqlDecimal;
    }

    public void setMysqlDecimal(BigDecimal mysqlDecimal) {
        this.mysqlDecimal = mysqlDecimal;
    }

    public Boolean getMysqlBoolean() {
        return mysqlBoolean;
    }

    public void setMysqlBoolean(Boolean mysqlBoolean) {
        this.mysqlBoolean = mysqlBoolean;
    }

    public Date getMysqlDate() {
        return mysqlDate;
    }

    public void setMysqlDate(Date mysqlDate) {
        this.mysqlDate = mysqlDate;
    }

    public Time getMysqlTime() {
        return mysqlTime;
    }

    public void setMysqlTime(Time mysqlTime) {
        this.mysqlTime = mysqlTime;
    }

    public Timestamp getMysqlDatetime() {
        return mysqlDatetime;
    }

    public void setMysqlDatetime(Timestamp mysqlDatetime) {
        this.mysqlDatetime = mysqlDatetime;
    }

    public Timestamp getMysqlTimestamp() {
        return mysqlTimestamp;
    }

    public void setMysqlTimestamp(Timestamp mysqlTimestamp) {
        this.mysqlTimestamp = mysqlTimestamp;
    }

    public String getMysqlYear() {
        return mysqlYear;
    }

    public void setMysqlYear(String mysqlYear) {
        this.mysqlYear = mysqlYear;
    }

    public Object[] getPrimaryKeys() {
        return new Object[]{this.acId};
    }

    public ACValidTestVO toVO() {
        ACValidTestVO vo = new ACValidTestVO();
        vo.setAcId(String.valueOf(this.acId));
        vo.setMysqlVarchar(this.mysqlVarchar);
        vo.setMysqlChar(this.mysqlChar);
        vo.setMysqlBlob(String.valueOf(this.mysqlBlob));
        vo.setMysqlText(this.mysqlText);
        vo.setMysqlInt(String.valueOf(this.mysqlInt));
        vo.setMysqlTinyint(String.valueOf(this.mysqlTinyint));
        vo.setMysqlSmallint(String.valueOf(this.mysqlSmallint));
        vo.setMysqlMediumint(String.valueOf(this.mysqlMediumint));
        vo.setMysqlBigint(String.valueOf(this.mysqlBigint));
        vo.setMysqlBit(String.valueOf(this.mysqlBit));
        vo.setMysqlFloat(String.valueOf(this.mysqlFloat));
        vo.setMysqlDouble(String.valueOf(this.mysqlDouble));
        vo.setMysqlDecimal(String.valueOf(this.mysqlDecimal));
        vo.setMysqlBoolean(String.valueOf(this.mysqlBoolean));
        vo.setMysqlDate(String.valueOf(this.mysqlDate));
        vo.setMysqlTime(String.valueOf(this.mysqlTime));
        vo.setMysqlDatetime(String.valueOf(this.mysqlDatetime));
        vo.setMysqlTimestamp(String.valueOf(this.mysqlTimestamp));
        vo.setMysqlYear(this.mysqlYear);

        return vo;
    }
}
