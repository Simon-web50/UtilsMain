package Time;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Time extends JFrame implements Runnable {
    private Thread clock;
    public Time(){
        super("时钟");//时钟标题
        this.setFont(new Font("Times New Roman",Font.BOLD,60));//设置时钟字体
        this.go();//开始运行
        this.setSize(280,100); //设置时钟大小
        this.setVisible(true);//设置窗口可见
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(1000);//1s更新1次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //初始化时钟
    public void go(){
        if (clock == null){
            clock = new Thread(this);
            clock.start();
        }
    }

    //绘制时钟上的时间
    @Override
    public void paint(Graphics g) {
        String s = "";
        Date now = new Date();
        System.out.println(now);
        s = now.toString().substring(11,19);
        g.setColor(Color.WHITE);
        Dimension dimension = getSize();
        g.fillRect(0,0,dimension.width,dimension.height);//时钟背景
        g.setColor(Color.red);//时钟字体
        g.drawString(s,20,80);
    }

    public static void main(String[] args) {
        Time time = new Time();//初始化对象
        time.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭后程序也停止
    }
}