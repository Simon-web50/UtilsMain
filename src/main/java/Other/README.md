### 2.1、语法更简洁
#### 传统函数
```java
const add = function(a, b) {
    return a + b;
}
```
#### 箭头函数
const add = (a, b) => a + b;

#### 箭头函数有一个参数的时候可以省略小括号
const add = a => {return a + b};

#### 箭头函数返回值只有一条语句时可以省略return和{}
const add = a => a + b;
#### 温馨提示：const声明的变量是不能重复的