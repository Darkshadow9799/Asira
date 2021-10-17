package com.dshaw.asira.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dshaw.asira.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SpeProjMSmTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpeProjMSm.class);
        SpeProjMSm speProjMSm1 = new SpeProjMSm();
        speProjMSm1.setId(1L);
        SpeProjMSm speProjMSm2 = new SpeProjMSm();
        speProjMSm2.setId(speProjMSm1.getId());
        assertThat(speProjMSm1).isEqualTo(speProjMSm2);
        speProjMSm2.setId(2L);
        assertThat(speProjMSm1).isNotEqualTo(speProjMSm2);
        speProjMSm1.setId(null);
        assertThat(speProjMSm1).isNotEqualTo(speProjMSm2);
    }
}
