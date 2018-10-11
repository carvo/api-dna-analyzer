package br.com.mercadolivre.mutantidentifier.analyzers.sequences;

import org.junit.Test;

import static org.junit.Assert.*;

public class SoutheastSequenceAnalyzerTest {

    @Test
    public void shouldFindOneSequence() {
        final String[] dna = {
                "ACGTATAA",
                "CCGTCCTA",
                "TTATGTCT",
                "AGTAGGAT",
                "CTCATAAT",
                "CAAGTTTC",
                "TAGGCTTC",
                "AGAATCTT"
        };

        final SoutheastSequenceAnalyzer analyzer = new SoutheastSequenceAnalyzer(4, dna.length);
        CommonExecutorForTest.commonExecution(dna, analyzer);

        assertEquals(1, analyzer.getCountMutantSequence());
    }

    @Test
    public void shouldFindTwoSequencesInSameOblique() {
        final String[] dna = {
                "TCGTATAA",
                "CTGTCCTA",
                "TTTTGTCT",
                "AGTTGGAT",
                "CTCATAAT",
                "CAAGTTTC",
                "TAGGCTTC",
                "AGAATCTT"
        };

        final SoutheastSequenceAnalyzer analyzer = new SoutheastSequenceAnalyzer(4, dna.length);
        CommonExecutorForTest.commonExecution(dna, analyzer);

        assertEquals(2, analyzer.getCountMutantSequence());
    }

}
