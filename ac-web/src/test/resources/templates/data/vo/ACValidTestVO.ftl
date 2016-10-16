package ${domainName}.${projectName}.${tableInfo.moduleName}.data.vo;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import ${domainName}.${projectName}.${tableInfo.moduleName}.data.po.${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}PO;
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
public class ${projectNameUpperCase}${tableInfo.moduleAndBusinessTableName}VO {

}
