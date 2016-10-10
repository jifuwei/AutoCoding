package com.jifuwei.ac.web.valid.data.vo;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.valid.data.po.ACValidTestPO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 测试VO
 * Created by JFW on 2016/10/6.
 */
public class ACValidTestVO {

    @NotBlank(message = "{ACValidTestVO.mysql_varchar.notblank}")
    @Length(min = 1, max = 255, message = "{ACValidTestVO.mysql_varchar.length}")
    private String mysql_varchar;//字符串

    @NotEmpty(message = "{ACValidTestVO.mysql_char.notempty}")
    private String mysql_char;//字符

    @NotEmpty(message = "{ACValidTestVO.mysql_blob.notempty}")
    private String mysql_blob;//二进制大对象
    private String mysql_text;//文本

    @Min(value = -2147483648, message = "{ACValidTestVO.mysql_int.min}")
    @Max(value = 2147483647, message = "{ACValidTestVO.mysql_int.max}")
    private String mysql_int;//整形
    private String mysql_tinyint;//小整形
    private String mysql_smallint;//短整形
    private String mysql_mediumint;//中整形
    private String mysql_bigint;//大整形
    private String mysql_bit;//比特

    @Digits(integer = 2, fraction = 2, message = "{ACValidTestVO.mysql_float.digits}")
    private String mysql_float;//浮点型
    private String mysql_double;//双精度型
    private String mysql_decimal;//小数
    private String mysql_boolean;//布尔型
    private String mysql_date;//日期
    private String mysql_time;//时间
    private String mysql_datetime;//日期时间
    private String mysql_timestamp;//时间戳
    private String mysql_year;//年

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

    public String getMysql_int() {
        return mysql_int;
    }

    public void setMysql_int(String mysql_int) {
        this.mysql_int = mysql_int;
    }

    public String getMysql_tinyint() {
        return mysql_tinyint;
    }

    public void setMysql_tinyint(String mysql_tinyint) {
        this.mysql_tinyint = mysql_tinyint;
    }

    public String getMysql_smallint() {
        return mysql_smallint;
    }

    public void setMysql_smallint(String mysql_smallint) {
        this.mysql_smallint = mysql_smallint;
    }

    public String getMysql_mediumint() {
        return mysql_mediumint;
    }

    public void setMysql_mediumint(String mysql_mediumint) {
        this.mysql_mediumint = mysql_mediumint;
    }

    public String getMysql_bit() {
        return mysql_bit;
    }

    public void setMysql_bit(String mysql_bit) {
        this.mysql_bit = mysql_bit;
    }

    public String getMysql_bigint() {
        return mysql_bigint;
    }

    public void setMysql_bigint(String mysql_bigint) {
        this.mysql_bigint = mysql_bigint;
    }

    public String getMysql_float() {
        return mysql_float;
    }

    public void setMysql_float(String mysql_float) {
        this.mysql_float = mysql_float;
    }

    public String getMysql_double() {
        return mysql_double;
    }

    public void setMysql_double(String mysql_double) {
        this.mysql_double = mysql_double;
    }

    public String getMysql_decimal() {
        return mysql_decimal;
    }

    public void setMysql_decimal(String mysql_decimal) {
        this.mysql_decimal = mysql_decimal;
    }

    public String getMysql_boolean() {
        return mysql_boolean;
    }

    public void setMysql_boolean(String mysql_boolean) {
        this.mysql_boolean = mysql_boolean;
    }

    public String getMysql_date() {
        return mysql_date;
    }

    public void setMysql_date(String mysql_date) {
        this.mysql_date = mysql_date;
    }

    public String getMysql_time() {
        return mysql_time;
    }

    public void setMysql_time(String mysql_time) {
        this.mysql_time = mysql_time;
    }

    public String getMysql_datetime() {
        return mysql_datetime;
    }

    public void setMysql_datetime(String mysql_datetime) {
        this.mysql_datetime = mysql_datetime;
    }

    public String getMysql_timestamp() {
        return mysql_timestamp;
    }

    public void setMysql_timestamp(String mysql_timestamp) {
        this.mysql_timestamp = mysql_timestamp;
    }

    public String getMysql_year() {
        return mysql_year;
    }

    public void setMysql_year(String mysql_year) {
        this.mysql_year = mysql_year;
    }

    public ACValidTestPO toPO() {
        ACValidTestPO po = new ACValidTestPO();
        try {
            po.setMysql_varchar(this.mysql_varchar);
            po.setMysql_char(this.mysql_char);
            po.setMysql_blob(this.mysql_blob.getBytes("UTF8"));
            po.setMysql_text(this.mysql_text);
            po.setMysql_int(Integer.valueOf(this.mysql_int));
            po.setMysql_tinyint(Integer.valueOf(this.mysql_tinyint));
            po.setMysql_smallint(Integer.valueOf(this.mysql_smallint));
            po.setMysql_mediumint(Integer.valueOf(this.mysql_mediumint));
            po.setMysql_bigint(new BigInteger(this.mysql_bigint));
            po.setMysql_bit(Boolean.parseBoolean(this.mysql_bit));
            po.setMysql_float(Float.valueOf(this.mysql_float));
            po.setMysql_double(Double.valueOf(this.mysql_double));
            po.setMysql_decimal(new BigDecimal(this.mysql_decimal));
            po.setMysql_boolean(Boolean.parseBoolean(this.mysql_boolean));
            po.setMysql_date(Date.valueOf(this.mysql_date));
            po.setMysql_time(Time.valueOf(this.mysql_time));
            po.setMysql_datetime(Timestamp.valueOf(this.mysql_datetime));
            po.setMysql_year(this.mysql_year);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return po;
    }
}
