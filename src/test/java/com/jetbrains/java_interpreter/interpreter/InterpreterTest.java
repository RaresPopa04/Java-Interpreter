package com.jetbrains.java_interpreter.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InterpreterTest {

    InterpreterService interpreterService;

    @BeforeEach
    void setUp() {
        interpreterService = new InterpreterService();
    }

    @Test
    void testSimplePrint() {
        String program = """
                    x = 1
                    print x
                """;
        assertThat(interpreterService.interpret(program)).isEqualTo("1\n");
    }

    @Test
    void testVariableAssignment() {
        String program = """
                    x = 1
                    y = x
                    print y
                """;
        assertThat(interpreterService.interpret(program)).isEqualTo("1\n");
    }

    @Test
    void testUndefined() {
        String program = """
                    print x
                """;
        assertThat(interpreterService.interpret(program)).isEqualTo("null\n");
    }

    @Test
    void testResetsScope() {
        String program = """
                    x = 1
                    print x
                """;
        assertThat(interpreterService.interpret(program)).isEqualTo("1\n");

        String secondProgram = """
                    print x
                """;
        assertThat(interpreterService.interpret(secondProgram)).isEqualTo("null\n");
    }

    @Test
    void testNestedScope() {
        String program = """
                    x = 1
                    print x
                    scope {
                        x = 2
                        print x
                        scope {
                            x = 3
                            y = x
                            print x
                            print y
                        }
                        print x
                        print y
                    }
                    print x
                """;
        assertThat(interpreterService.interpret(program)).isEqualTo("1\n2\n3\n3\n2\nnull\n1\n");
    }
}
