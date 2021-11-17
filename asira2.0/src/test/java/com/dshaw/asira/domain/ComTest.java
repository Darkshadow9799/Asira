package com.dshaw.asira.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dshaw.asira.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ComTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Com.class);
        Com com1 = new Com();
        com1.setId(1L);
        Com com2 = new Com();
        com2.setId(com1.getId());
        assertThat(com1).isEqualTo(com2);
        com2.setId(2L);
        assertThat(com1).isNotEqualTo(com2);
        com1.setId(null);
        assertThat(com1).isNotEqualTo(com2);
    }
}
