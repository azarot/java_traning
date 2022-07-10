package com.azarot.homework.hw15;


import com.azarot.homework.hw15.context.PackageScanningApplicationContext;
import com.azarot.homework.hw15.exception.NoSuchBeanException;
import com.azarot.homework.hw15.exception.NoUniqueBeanException;
import com.azarot.homework.hw15.mock.HelloService;
import com.azarot.homework.hw15.mock.HowAreYouService;
import com.azarot.homework.hw15.mock.TalkingService;
import com.azarot.homework.hw15.mock.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextTest {

    public static final String MOCK_PACKAGE = "com.azarot.homework.h15.mock";
    public static final String ERROR_MOCK_PACKAGE = "com.azarot.homework.h15.errormock";

    @Test
    public void shouldThrowExceptionIfBeanDoesNotHaveDefaultConstructor() {
        assertThrows(RuntimeException.class, () -> new PackageScanningApplicationContext(ERROR_MOCK_PACKAGE));
    }

    @Test
    public void shouldFindBeanByType() {
        var applicationContext = new PackageScanningApplicationContext(MOCK_PACKAGE);
        HelloService bean = applicationContext.getBean(HelloService.class);
        assertNotNull(bean);
    }

    @Test
    public void shouldThrowNoSuchBeanException() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);

        assertThrows(NoSuchBeanException.class, () -> context.getBean(Utils.class));
    }

    @Test
    public void shouldThrowNoUniqueBeanException() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);

        assertThrows(NoUniqueBeanException.class, () -> context.getBean(TalkingService.class));
    }

    @Test
    public void shouldFindBeanByProvidedName() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        assertNotNull(context.getBean("hiService", TalkingService.class));
    }

    @Test
    public void shouldFindBeanByDefaultName() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        assertNotNull(context.getBean("howAreYouService", HowAreYouService.class));
    }

    @Test
    public void shouldThrowNoSuchBeanExceptionIfThereIsNoBeanWithSuchName() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        assertThrows(NoSuchBeanException.class, () -> context.getBean("invalidName", TalkingService.class));
    }

    @Test
    public void shouldThrowNoSuchBeanExceptionIfThereIsNoBeanWithSuchType() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        assertThrows(NoSuchBeanException.class, () -> context.getBean("hiService", Utils.class));
    }

    @Test
    public void shouldGetAllBeans() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        var allBeans = context.getAllBeans(TalkingService.class);
        assertEquals(2, allBeans.size());
        assertEquals(allBeans.get("hiService").getClass(), HelloService.class);
        assertEquals(allBeans.get("howAreYouService").getClass(), HowAreYouService.class);
    }

    @Test
    public void shouldReturnEmptyMap() {
        var context = new PackageScanningApplicationContext(MOCK_PACKAGE);
        var allBeans = context.getAllBeans(Utils.class);
        assertEquals(0, allBeans.size());
    }
}
