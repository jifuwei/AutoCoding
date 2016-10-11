package com.jifuwei.ac.web.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试基类，用于继承
 * Created by JFW on 2016/10/11.
 */
@Transactional
@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml", "classpath:spring/applicationContext-dataSource-test.xml", "classpath:spring/applicationContext-transaction-test.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
}
