import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FB3_1Test {

    @Test
    void solution0() {
        assertEquals("0", FB3_1.solution("1", "1"));
    }

    @Test
    void solution1() {
        assertEquals("1", FB3_1.solution("1", "2"));
        assertEquals("1", FB3_1.solution("2", "1"));
    }

    @Test
    void solution2() {
        assertEquals("2", FB3_1.solution("1", "3"));
        assertEquals("2", FB3_1.solution("3", "1"));
        assertEquals("2", FB3_1.solution("3", "2"));
        assertEquals("2", FB3_1.solution("2", "3"));
    }

    @Test
    void solution4() {
        assertEquals("3", FB3_1.solution("1", "4"));
        assertEquals("3", FB3_1.solution("4", "1"));
        assertEquals("3", FB3_1.solution("3", "4"));
        assertEquals("3", FB3_1.solution("4", "3"));
        assertEquals("3", FB3_1.solution("3", "5"));
        assertEquals("3", FB3_1.solution("5", "3"));
        assertEquals("3", FB3_1.solution("2", "5"));
        assertEquals("3", FB3_1.solution("5", "2"));
    }

    @Test
    void solution5() {
        assertEquals("4", FB3_1.solution("1", "5"));
        assertEquals("4", FB3_1.solution("5", "1"));
        assertEquals("4", FB3_1.solution("5", "4"));
        assertEquals("4", FB3_1.solution("4", "5"));
        assertEquals("4", FB3_1.solution("4", "7"));
        assertEquals("4", FB3_1.solution("7", "4"));
        assertEquals("4", FB3_1.solution("3", "7"));
        assertEquals("4", FB3_1.solution("7", "3"));
        assertEquals("4", FB3_1.solution("3", "8"));
        assertEquals("4", FB3_1.solution("8", "3"));
        assertEquals("4", FB3_1.solution("5", "8"));
        assertEquals("4", FB3_1.solution("8", "5"));
        assertEquals("4", FB3_1.solution("5", "7"));
        assertEquals("4", FB3_1.solution("7", "5"));
        assertEquals("4", FB3_1.solution("2", "7"));
        assertEquals("4", FB3_1.solution("7", "2"));
    }

    @Test
    void solution_different() {
        assertEquals("5", FB3_1.solution("11" ,"4"));
        assertEquals("6", FB3_1.solution("9" ,"13"));
        assertEquals("7", FB3_1.solution("21" ,"29"));
        assertEquals("10", FB3_1.solution("4" ,"31"));
        assertEquals("16", FB3_1.solution("425" ,"277"));
        assertEquals("10000000000000000000", FB3_1.solution("1" ,"10000000000000000001"));
        assertEquals("10000000000000000000", FB3_1.solution("10000000000000000001" ,"1"));
    }

    @Test
    void solutionLarge() {
        assertEquals("9999999999999999999999999999999999999999999999998", FB3_1.solution("1" ,"9999999999999999999999999999999999999999999999999"));
        assertEquals("9999999999999999999999999999999999999999999999998", FB3_1.solution("9999999999999999999999999999999999999999999999999" ,"1"));

        assertEquals("3333333333333333333333333333333333333333333333334", FB3_1.solution("3" ,"9999999999999999999999999999999999999999999999998"));
        assertEquals("3333333333333333333333333333333333333333333333334", FB3_1.solution("9999999999999999999999999999999999999999999999998" ,"3"));

        assertEquals("99999999999999999999999999999999999999999999999999", FB3_1.solution("1" ,"100000000000000000000000000000000000000000000000000"));
        assertEquals("99999999999999999999999999999999999999999999999999", FB3_1.solution("100000000000000000000000000000000000000000000000000" ,"1"));

        assertEquals("impossible", FB3_1.solution("2" ,"100000000000000000000000000000000000000000000000000"));
        assertEquals("impossible", FB3_1.solution("100000000000000000000000000000000000000000000000000" ,"2"));

        assertEquals("33333333333333333333333333333333333333333333333335", FB3_1.solution("3" ,"100000000000000000000000000000000000000000000000000"));
        assertEquals("33333333333333333333333333333333333333333333333335", FB3_1.solution("100000000000000000000000000000000000000000000000000" ,"3"));

        assertEquals("99999999999999999999999999999999999999999999999999", FB3_1.solution("99999999999999999999999999999999999999999999999999" ,"100000000000000000000000000000000000000000000000000"));
        assertEquals("99999999999999999999999999999999999999999999999999", FB3_1.solution("100000000000000000000000000000000000000000000000000" ,"99999999999999999999999999999999999999999999999999"));


    }

    @Test
    void solution_impossible() {
        assertEquals("impossible", FB3_1.solution("2" ,"4"));
    }
}