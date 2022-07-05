package com.azarot.homework.h15;


import com.azarot.homework.h15.mock.SimpleBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    public void shouldFindBeanByType() {
        var applicationContext = new ApplicationContextImpl("com.azarot.homework.h15.mock");
        SimpleBean bean = applicationContext.getBean(SimpleBean.class);
        assertNotNull(bean);
    }
}
