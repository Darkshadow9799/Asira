package com.dshaw.asira.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dshaw.asira.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SpeProjTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpeProj.class);
        SpeProj speProj1 = new SpeProj();
        speProj1.setId(1L);
        SpeProj speProj2 = new SpeProj();
        speProj2.setId(speProj1.getId());
        assertThat(speProj1).isEqualTo(speProj2);
        speProj2.setId(2L);
        assertThat(speProj1).isNotEqualTo(speProj2);
        speProj1.setId(null);
        assertThat(speProj1).isNotEqualTo(speProj2);
    }
}
