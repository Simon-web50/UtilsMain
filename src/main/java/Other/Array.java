package Other;

import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        // 数组的动态初始化
        int[] array = new int[10];
        /**
         * int[] array;
         * array = new int[10];
         */

        // 静态初始化
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        /**
         * int[] array1;
         * array1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
         */
        // for-each循环无法获得对应下标
        for (int array2 : array1) {
            System.out.print(array2);
        };
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i]);
        };
        for (int i = 0; i < 10; i++) {
            System.out.print(array1[i]);
        };

        // 数组的修改
        array1[0] = 10;
        System.out.println(array1[0]);

        // 按照C语言格式创建
        /**
         * 该种定义方式不太友好,容易造成数组的类型就是int的误解.
         * []如果在类型之后,就表示数组类型,因此int[]在一起写思路更清晰.
         */
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String newArr = Arrays.toString(arr);
        System.out.println(newArr);

        /**
         * 如果没有对数组进行初始化,数组中元素有默认值.
         */
        float[] floats = new float[1];
        System.out.println(floats[0]);
        double[] doubles = new double[1];
        System.out.println(doubles[0]);
        char[] chars = new char[1];
        System.out.println(chars[0]);
        boolean[] booleans = new boolean[1];
        System.out.println(booleans[0]);

        System.out.println("=======================================================");

        // newArray 和 array3引用的是同一个数组,因此newArr修改空间中内容之后，arr也可以看到修改的结果
        int[] array3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] newArray = array3;
        newArray[0] = 99;
        System.out.println(array3[0]);

        // 使用Arrays中的copyOf方法完成数组的拷贝,copyOf方法在进行数组拷贝时，创建了一个新的数组,arr和newArr引用的不是同一个数组
        array3[0] = 100;
        newArray = Arrays.copyOf(array3, array3.length);
        System.out.println("newArr: " + Arrays.toString(newArray));

        // 因为arr修改其引用数组中内容时，对newArr没有任何影响
        array3[0] = 10;
        System.out.println("arr: " + Arrays.toString(array3));
        System.out.println("newArr: " + Arrays.toString(newArray));

        int[] newArr2 = Arrays.copyOfRange(array3, 2, 4);
        System.out.println("newArr2: " + Arrays.toString(newArr2));
        //System.arraycopy也是拷贝，第一个参数是要拷贝的原数组，第二个参数是原数组的下标位置
        //第三个参数是目标数组，第四个参数是目标数组的下标位置，第五个参数是要拷贝的长度
        int[] newArr4 = new int[array3.length];
        System.arraycopy(array3,0,newArr4,0, array3.length);
        System.out.println(Arrays.toString(array3));

        //数据类型[][] 数组名称 = new 数据类型 [行数][列数] { 初始化数据 };
//二维数组的定义，三种方式
        int[][] array4 = {{1, 2, 3}, {4, 5, 6}};
        int[][] arr1 = new int[2][];//二维数组可以省略列，但不能省略行
        int[][] arr2 = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(array.length);//数组行数
        System.out.println(array4[0].length);//数组列数
        System.out.println(array4[1].length);//数组列数

        int[][] array5 = {{1, 2, 3}, {4, 5, 6}};
        //遍历二维数组，三种方法
        //第一种方法
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array5[i].length; j++) {
                System.out.print(array5[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("=====");
        //第二种方法：for each
        for(int[] tmp : array5){//左边是数组的每个元素，右边是数组名
            for (int x:tmp) {
                System.out.print(x+" ");
            }
            System.out.println();
        }
        System.out.println("=====");
        //第三种方法，使用类方法
        String ret=Arrays.deepToString(array5);
        System.out.println(ret);
    }
}
