package org.swordess.toy.jdk8;

import org.junit.Assert;
import org.junit.Test;

public class DefaultMethodsTest {

    @Test
    public void test() {
        Assert.assertEquals("General", new General(){}.behavior());
        Assert.assertEquals("LessGeneralImpl", new LessGeneralImpl().behavior());
    }

    private static interface General {

        default String behavior() {
            return "General";
        }

    }

    private static interface LessGeneral extends General {

        String behavior();

    }

    private static class LessGeneralImpl implements LessGeneral {

        @Override
        public String behavior() {
            return "LessGeneralImpl";
        }

    }

}
