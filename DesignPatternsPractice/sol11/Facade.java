package DesignPatternsPractice.sol11;

public class Facade {
    // 在计算机主机中，只需要按下主机的开机按钮（On()),即可调用其它硬件设备和软件的启动方法，
    // 如内存的自检(check())、CPU的运行(Run())、硬盘的读取(Read())、操作系统的载入（Load())等，
    // 如果某一过程发生错误则启动失败。试使用外观模式模拟该过程，并绘制类图。
    private Board board;
    private CPU cpu;
    private Sys sys;

    public Facade(){
        this.board = new Board();
        this.cpu = new CPU();
        this.sys = new Sys();
    }

    public void On(){
        board.check();
        cpu.run();
        sys.load();
    }
}
