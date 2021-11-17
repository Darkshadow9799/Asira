package com.dshaw.asira.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dshaw.asira.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SpeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Spe.class);
        Spe spe1 = new Spe();
        spe1.setId(1L);
        Spe spe2 = new Spe();
        spe2.setId(spe1.getId());
        assertThat(spe1).isEqualTo(spe2);
        spe2.setId(2L);
        assertThat(spe1).isNotEqualTo(spe2);
        spe1.setId(null);
        assertThat(spe1).isNotEqualTo(spe2);
    }
}
