package com.azarot.homework.h15;


import com.azarot.homework.h15.mock.SimpleBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextTest {

    public static final String MOCK_PACKAGE = "com.azarot.homework.h15.mock";
    public static final String ERROR_MOCK_PACKAGE = "com.azarot.homework.h15.errormock";

    @Test
    public void shouldFindBeanByType() {
        var applicationContext = new PackageScanningApplicationContext(MOCK_PACKAGE);
        SimpleBean bean = applicationContext.getBean(SimpleBean.class);
        assertNotNull(bean);
    }

    @Test
    public void shouldThrowExceptionIfBeanDoesNotHaveDefaultConstructor() {
        assertThrows(RuntimeException.class, () -> new PackageScanningApplicationContext(ERROR_MOCK_PACKAGE));
    }
}
