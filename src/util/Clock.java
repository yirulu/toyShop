package util;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Clock extends JLabel implements Runnable {
    private static final long serialVersionUID = 1L;
    private Thread thread;

    public Clock() {
        start();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String dateTime = sdf.format(new Date());
            setText(dateTime);

            try {
                Thread.sleep(1000); // 更新頻率：每秒更新一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) {
        // 測試
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Clock Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Clock clock = new Clock();
            frame.getContentPane().add(clock);

            frame.pack();
            frame.setVisible(true);
        });
    }
}

