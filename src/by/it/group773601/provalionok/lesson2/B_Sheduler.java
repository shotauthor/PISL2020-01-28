package by.it.group773601.provalionok.lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {

        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }

    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.

        int start = 0;
        int stop = 0;

        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.

        List<Event> sortedList;
        sortedList = Arrays.asList(events);
        sortedList.sort((event1, event2) -> {
                    if (event1.start<event2.start){
                        return -1;
                    }else if(event1.start>event2.start){return 1;}
                    else {
                        return Integer.compare(event1.stop, event2.stop);
                    }
                }
        );

        result.add(sortedList.get(0));
        start = result.get(0).start;
        stop = result.get(0).stop;

        for (Event event : sortedList) {
            if (event.start != start&&event.start >= stop) {
                result.add(event);
                start = event.start;
                stop = event.stop;
            }
        }



            return result;                        //вернем итог

    }
}