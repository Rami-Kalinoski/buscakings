package myswing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro {
    // attributes
    private static int seconds = 0;
    private static int minutes = 0;
    private static Timer timer;
    private static Timer pauseTimer;
    // methods
    public static void startTimer(JLabel timerLabel) {
        /* comienza a correr el cronómetro asíncronamente */
        ActionListener timerAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                // actualizar tiempo en la interfaz
                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
            }
        };

        timer = new Timer(1000, timerAction);
        timer.start();
    }
    public static void stopTimer() {
        /* detiene el correr del cronómetro */
        if (timer != null) {
            timer.stop();
        }
    }
    public static void resumeTimer(JLabel timerLabel) {
        /* continúa el cronómetro asíncronamente */
        if (timer != null) {
            timer.restart();
        } else {
            startTimer(timerLabel);
        }
    }
    public static void resetTimer(JLabel timerLabel) {
        /* reinicia el cronómetro en 0 */
        seconds = 0;
        minutes = 0;
        if (timer != null) {
            timer.stop();
        }
        // actualizar tiempo en la interfaz
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
    public static void pauseForSeconds(JLabel timerLabel, int pauseSeconds) {
        /* detiene el correr del cronómetro por una cantidad de segundos recibida por parámetro */
        stopTimer(); // Pausar el cronómetro

        ActionListener pauseAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeTimer(timerLabel); // Reanudar el cronómetro después de la pausa
                pauseTimer.stop(); // Detener el temporizador de pausa
            }
        };

        pauseTimer = new Timer(pauseSeconds * 1000, pauseAction);
        pauseTimer.setRepeats(false); // Asegurar que solo se ejecute una vez
        pauseTimer.start();
    }

    // getters & setters
    public static int getSeconds() {
        return seconds;
    }
    public static void setSeconds(int seconds) {
        Cronometro.seconds = seconds;
    }
    public static int getMinutes() {
        return minutes;
    }
    public static void setMinutes(int minutes) {
        Cronometro.minutes = minutes;
    }
    public static Timer getTimer() {
        return timer;
    }
    public static void setTimer(Timer timer) {
        Cronometro.timer = timer;
    }
}