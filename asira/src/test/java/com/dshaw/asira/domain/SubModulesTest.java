package com.dshaw.asira.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dshaw.asira.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SubModulesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubModules.class);
        SubModules subModules1 = new SubModules();
        subModules1.setId(1L);
        SubModules subModules2 = new SubModules();
        subModules2.setId(subModules1.getId());
        assertThat(subModules1).isEqualTo(subModules2);
        subModules2.setId(2L);
        assertThat(subModules1).isNotEqualTo(subModules2);
        subModules1.setId(null);
        assertThat(subModules1).isNotEqualTo(subModules2);
    }
}
