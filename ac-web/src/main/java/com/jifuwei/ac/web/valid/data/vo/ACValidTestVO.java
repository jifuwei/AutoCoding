package com.jifuwei.ac.web.valid.data.vo;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import com.jifuwei.ac.web.valid.data.po.ACValidTestPO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @NotEmpty(message = "{ACValidTestVO.acId.notempty}", groups = {PrimaryKeyGroup.class})
    private String acId;

    @NotBlank(message = "{ACValidTestVO.mysqlVarchar.notblank}", groups = {EntityGroup.class})
    @Length(min = 1, max = 255, message = "{ACValidTestVO.mysqlVarchar.length}", groups = {EntityGroup.class})
    private String mysqlVarchar;//字符串

    @NotEmpty(message = "{ACValidTestVO.mysqlChar.notempty}", groups = {EntityGroup.class})
    private String mysqlChar;//字符

    @NotEmpty(message = "{ACValidTestVO.mysqlBlob.notempty}", groups = {EntityGroup.class})
    private String mysqlBlob;//二进制大对象
    private String mysqlText;//文本

    @Min(value = -2147483648, message = "{ACValidTestVO.mysqlInt.min}", groups = {EntityGroup.class})
    @Max(value = 2147483647, message = "{ACValidTestVO.mysqlInt.max}", groups = {EntityGroup.class})
    private String mysqlInt;//整形
    private String mysqlTinyint;//小整形
    private String mysqlSmallint;//短整形
    private String mysqlMediumint;//中整形
    private String mysqlBigint;//大整形
    private String mysqlBit;//比特

    @Digits(integer = 2, fraction = 2, message = "{ACValidTestVO.mysqlFloat.digits}", groups = {EntityGroup.class})
    private String mysqlFloat;//浮点型
    private String mysqlDouble;//双精度型
    private String mysqlDecimal;//小数
    private String mysqlBoolean;//布尔型
    private String mysqlDate;//日期
    private String mysqlTime;//时间
    private String mysqlDatetime;//日期时间
    private String mysqlTimestamp;//时间戳
    private String mysqlYear;//年

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
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

    public String getMysqlBlob() {
        return mysqlBlob;
    }

    public void setMysqlBlob(String mysqlBlob) {
        this.mysqlBlob = mysqlBlob;
    }

    public String getMysqlText() {
        return mysqlText;
    }

    public void setMysqlText(String mysqlText) {
        this.mysqlText = mysqlText;
    }

    public String getMysqlInt() {
        return mysqlInt;
    }

    public void setMysqlInt(String mysqlInt) {
        this.mysqlInt = mysqlInt;
    }

    public String getMysqlTinyint() {
        return mysqlTinyint;
    }

    public void setMysqlTinyint(String mysqlTinyint) {
        this.mysqlTinyint = mysqlTinyint;
    }

    public String getMysqlSmallint() {
        return mysqlSmallint;
    }

    public void setMysqlSmallint(String mysqlSmallint) {
        this.mysqlSmallint = mysqlSmallint;
    }

    public String getMysqlMediumint() {
        return mysqlMediumint;
    }

    public void setMysqlMediumint(String mysqlMediumint) {
        this.mysqlMediumint = mysqlMediumint;
    }

    public String getMysqlBigint() {
        return mysqlBigint;
    }

    public void setMysqlBigint(String mysqlBigint) {
        this.mysqlBigint = mysqlBigint;
    }

    public String getMysqlBit() {
        return mysqlBit;
    }

    public void setMysqlBit(String mysqlBit) {
        this.mysqlBit = mysqlBit;
    }

    public String getMysqlFloat() {
        return mysqlFloat;
    }

    public void setMysqlFloat(String mysqlFloat) {
        this.mysqlFloat = mysqlFloat;
    }

    public String getMysqlDouble() {
        return mysqlDouble;
    }

    public void setMysqlDouble(String mysqlDouble) {
        this.mysqlDouble = mysqlDouble;
    }

    public String getMysqlDecimal() {
        return mysqlDecimal;
    }

    public void setMysqlDecimal(String mysqlDecimal) {
        this.mysqlDecimal = mysqlDecimal;
    }

    public String getMysqlBoolean() {
        return mysqlBoolean;
    }

    public void setMysqlBoolean(String mysqlBoolean) {
        this.mysqlBoolean = mysqlBoolean;
    }

    public String getMysqlDate() {
        return mysqlDate;
    }

    public void setMysqlDate(String mysqlDate) {
        this.mysqlDate = mysqlDate;
    }

    public String getMysqlTime() {
        return mysqlTime;
    }

    public void setMysqlTime(String mysqlTime) {
        this.mysqlTime = mysqlTime;
    }

    public String getMysqlDatetime() {
        return mysqlDatetime;
    }

    public void setMysqlDatetime(String mysqlDatetime) {
        this.mysqlDatetime = mysqlDatetime;
    }

    public String getMysqlTimestamp() {
        return mysqlTimestamp;
    }

    public void setMysqlTimestamp(String mysqlTimestamp) {
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

    public ACValidTestPO toPO() {
        ACValidTestPO po = new ACValidTestPO();
        try {
            po.setAcId(StringUtils.isBlank(this.acId) ? null : Integer.parseInt(this.acId)); //注意：主键如果是自增长时需要考虑为空情况
            po.setMysqlVarchar(this.mysqlVarchar);
            po.setMysqlChar(this.mysqlChar);
            po.setMysqlBlob(this.mysqlBlob.getBytes("UTF8"));
            po.setMysqlText(this.mysqlText);
            po.setMysqlInt(Integer.valueOf(this.mysqlInt));
            po.setMysqlTinyint(Integer.valueOf(this.mysqlTinyint));
            po.setMysqlSmallint(Integer.valueOf(this.mysqlSmallint));
            po.setMysqlMediumint(Integer.valueOf(this.mysqlMediumint));
            po.setMysqlBigint(new BigInteger(this.mysqlBigint));
            po.setMysqlBit(Boolean.parseBoolean(this.mysqlBit));
            po.setMysqlFloat(Float.valueOf(this.mysqlFloat));
            po.setMysqlDouble(Double.valueOf(this.mysqlDouble));
            po.setMysqlDecimal(new BigDecimal(this.mysqlDecimal));
            po.setMysqlBoolean(Boolean.parseBoolean(this.mysqlBoolean));
            po.setMysqlDate(Date.valueOf(this.mysqlDate));
            po.setMysqlTime(Time.valueOf(this.mysqlTime));
            po.setMysqlDatetime(Timestamp.valueOf(this.mysqlDatetime));
            po.setMysqlYear(this.mysqlYear);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ACServiceException(ACErrorMsg.ERROR_DATA_IS_MALFORM);
        }

        return po;
    }
}
