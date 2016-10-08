package com.jifuwei.ac.foundation.base;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JFW on 2016/10/5.
 */
@Transactional
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
}
