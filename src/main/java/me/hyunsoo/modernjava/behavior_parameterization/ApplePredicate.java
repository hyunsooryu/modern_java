package me.hyunsoo.modernjava.behavior_parameterization;

/**
 * 사과를 선택하는 다양한 전략을 만들어낸다.
 * 디자인 패턴에서 -> Strategy_Pattern
 * 전략 패턴은 각 알고리즘을 캡슐화 하여 알고리즘 패밀리를 정의해 둔 다음 런타임에 알고리즘을 선택하는 기법이다.
 */

public interface ApplePredicate {
    boolean test(Apple apple);
}
