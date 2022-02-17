package me.hyunsoo.modernjava.behavior_parameterization;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 현수의 modern java - 1차시
 * 1. Behavior Parameterization
 * 동작 파라미터화 - Behavior parameterization
 * 메서드의 동작을 파라미터화 하는 것.
 * 원하는 동작을, 매서드의 인수로 전달할 수 있다.
 */

public class PlayGround {




    public static void main(String[] args) {
        List<Apple> apples = List.of(new Apple(Color.RED, 2), new Apple(Color.GREEN, 4), new Apple(Color.GREEN, 4));

        //익명 클래스
        List<Apple> result = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return 150 < apple.getWeight() && Color.GREEN.equals(apple.getColor());
            }
        });

        //람다
        List<Apple> result2 = filterApples(apples, apple -> 150 < apple.getWeight() && Color.GREEN.equals(apple.getColor()));

        //제너릭
        List<Apple> result3 = filter(apples, apple-> 150 < apple.getWeight() && Color.GREEN.equals(apple.getColor()));
        List<Integer> result4 = filter(List.of(1,2,3,4,5), i->i%2==0);
        List<String> result5 = filter(List.of("ABC","AB", "A"), s->s.startsWith("AB"));

    }

    //녹색 사과만 필터링 하는 코드 - 첫번째
    public static List<Apple> filterGreenApple(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(Color.GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
    /**
     * 녹색말고, 빨간색을 필터링 하는 기능이 필요하면 어떻게 해야할까? 대신 중복하지 않고 말이다.
     */

    //원하는 색 사과만 필터링 하는 코드 - 두번째
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 갑자기, 색 이외에 무게로 필터링 하는 기능을 추가하고 싶달고 한다. 어떻게 해야할까?
     *
     */
    //원하는 색 무게로 필터링 하는 코드
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(weight < apple.getWeight()){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 위 코드도 나름 좋은 해결책이지만, 하지만 구현 코드를 자세히 살펴 보면 목록을 검색하고, 각 사과에 필터링 조건을 적용하는 부분의 코드가 색 필터링 코드와 대부분 중복된다.
     * 소프트웨어 공학희 DRY(Don't Repeat Yourself) 원칙을 어기는 것이라고 할 수 있다.
     * 탐색 과정을 고쳐서, 성능을 개선하기 위해서는 어떻게 해야할까?
     * boolean 변수를 하나더 메소드의 파람으로 넘겨 true 일때는 색상, false 일때는 무게로 필터링 하게 할까?
     * 위와 같은 방법은 명시적으로나, 엔지니어링적 관점으로나 절대 구현해서는 안되는 방식이다.
     *
     * 우리는 이때, modern java의 Behavior Parameterization 개념을 적용해 볼 수 있다.
     * 아래 처럼 ApplePredicate 이라는 인터페이스를 만들고, 원하는 클래스 인스턴스를 넣어준다면? 비로소 동작을 파라미터화 했다고 할 수 있다.
     */

     //아주 좋은 코드
     public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
         List<Apple> result = new ArrayList<>();
         for(Apple apple : inventory){
             if(p.test(apple)){
                 result.add(apple);
             }
         }
         return result;
     }

    /**
     * 조금 더 나아가보자. 클래스를 일일이 필요할 때 마다 작성하는 것. 힘들지않을까? 수고가 간다. 이를 개선할 방법은 없나?
     * 자바 클래스의 선언과 인스턴스화를 동시에 수핼 할 수 있도록 익명 클래스라는 기법을 제공하고 있지않나. 이를 활용하면 된다.
     * main 함수 23 line 참고
     */

    /**
     * 아주 좋은데, 조금 아쉽다. 익명 클래스 조차 반복되어 지저분한 코드를 양산한다.
     * 이때 꺼내 사용할 수있는것이 바로 자바8의 람다 표현식이다.
     * main 함수 30 line 참고
     */

    /**
     * 마지막이다. 꼭 사과 리스트 만? 아니다. 유연하고 간결하게 다양한 종류의 리스트를 필터링하는 훌륭한 코드를 작성해보자.
     * 제너릭을 활용해보자.
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }








}
