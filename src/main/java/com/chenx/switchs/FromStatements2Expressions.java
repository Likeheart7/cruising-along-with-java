package com.chenx.switchs;

import java.util.List;

/*
模式匹配让switch从statement变成了expression
 */
public class FromStatements2Expressions {
    public static void main(String[] args) {
        List.of(59, 64, 76, 82, 89, 94, 100)
            .stream()
            .map(Grade::gradeFor)
            .forEach(System.out::println);
    }

    static class Grade {
        public static char gradeFor(int score) {
//            return withIf(score);
//            return withSwitch(score);
            return withSwitchExpression(score);
        }

        private static char withIf(int score) {
            char letter = 0;
            if (score >= 90) {
                letter = 'A';
            } else if (score >= 80) {
                letter = 'B';
            } else if (score >= 70) {
                letter = 'C';
            } else if (score >= 60) {
                letter = 'D';
            } else if (score >= 50) {
                letter = 'F';
            }
            return letter;
        }

        private static char withSwitch(int score) {
            char letter = 0;
            switch (Math.min(score / 10, 10)) {
                case 10, 9:
                    letter = 'A';
                    break;
                case 8:
                    letter = 'B';
                    break;
                case 7:
                    letter = 'C';
                    break;
                case 6:
                    letter = 'D';
                    break;
                default:
                    letter = 'F';
            }
            return letter;
        }

        private static char withSwitchExpression(int score) {
            return switch (Math.min(score/10, 10)) {
                case 10, 9 -> {
                    System.out.print("You got A! Congratulations!\t");
                    yield 'A'; // switch使用yield放回，以便与return 区分
                }
                case 8 -> 'B';
                case 7 -> 'C';
                case 6 -> 'D';
                default -> 'F';
            };
        }
        private static char withSwitchExpressionAndEnum(int score) {
            return switch (Math.min(score/10, 10)) {
                case 10, 9 -> {
                    System.out.print("You got A! Congratulations!\t");
                    yield 'A'; // switch使用yield放回，以便与return 区分
                }
                case 8 -> 'B';
                case 7 -> 'C';
                case 6 -> 'D';
                default -> 'F'; // 可以使用枚举来省略掉这个default
            };
        }
    }
}


