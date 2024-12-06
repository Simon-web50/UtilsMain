package Other;

import java.util.*;

public class SortTest {
    public static void main(String[] args) {
        List<Sort> list = new ArrayList<>();
        list.add(new Sort(103,25,"关羽"));
        list.add(new Sort(104,21,"张飞"));
        list.add(new Sort(108,18,"刘备"));
        list.add(new Sort(101,32,"袁绍"));
        list.add(new Sort(109,36,"赵云"));
        list.add(new Sort(103,16,"曹操"));
        System.out.println("排序前:");
        for(Sort sort : list){
            System.out.println(sort.toString());
        }
        System.out.println("默认排序后:");
        Collections.sort(list);
        for (Sort sort : list){
            System.out.println(sort.toString());
        }
        //自定义排序：使用匿名内部类，实现Comparator接口，重写compare方法
        Collections.sort(list, new Comparator<Sort>() {
            @Override
            public int compare(Sort o1, Sort o2) {
                return o1.getId() - o2.getId();
            }
        });
        System.out.println("自定义ID排序后:");
        for(Sort sort : list){
            System.out.println(sort.toString());
        }
        //自定义排序2
        list.sort(new Comparator<Sort>() {
            @Override
            public int compare(Sort o1, Sort o2) {
                return o1.getId() - o2.getId();
            }
        });
        //2.Arrays.sort(int[] a, int fromIndex, int toIndex)
        //规则为从fromIndex<= a数组 <toIndex
        int[] a = new int[]{2, 5, 4, 1, 19, 3, 2};
        Arrays.sort(a,1,4);
        for(int x : a) {
            System.out.print(x + " ");
        }

        /**
         * 要实现降序排序，得通过包装类型的数组来实现，基本数据类型数组是不行的
         */
        System.out.println("java自带的Collections.reverseOrder():");
        Integer[] integers = new Integer[]{10, 293, 35, 24, 64, 56};
        Arrays.sort(integers, Collections.reverseOrder());
        for (Integer integer : integers) System.out.print(integer + " ");
    }
}
