package br.com.mercadolivre.mutantidentifier.analyzers.sequences;

import org.junit.Test;

import static org.junit.Assert.*;

public class SouthwestSequenceAnalyzerTest {

    @Test
    public void shouldFindOneSequence() {
        final String[] dna = {
                "ACGTATAA",
                "CCGTCCTA",
                "TTATGTCT",
                "AGTAGGAT",
                "CTCATAAT",
                "CAAGTTTC",
                "TAGGCCTC",
                "AGAATCTT"
        };

        final SouthwestSequenceAnalyzer analyzer = new SouthwestSequenceAnalyzer(4, dna.length);
        CommonExecutorForTest.commonExecution(dna, analyzer);

        assertEquals(1, analyzer.getCountMutantSequence());
    }

    @Test
    public void shouldFindTwoSequencesInSameOblique() {
        final String[] dna = {
                "ACGTATAA",
                "CCGTCCAA",
                "TTATGACT",
                "AGTAAGAT",
                "CTCATAAT",
                "CAAGTTTC",
                "TAGGCCTC",
                "AGAATCTT"
        };

        final SouthwestSequenceAnalyzer analyzer = new SouthwestSequenceAnalyzer(4, dna.length);
        CommonExecutorForTest.commonExecution(dna, analyzer);

        assertEquals(2, analyzer.getCountMutantSequence());
    }

}
