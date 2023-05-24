import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;

public class TecladoSom extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final String[] TECLAS = {"A", "S", "D", "F", "G", "H", "J", "K", "L", "Ç"};
    private static final String[] SOMS = {"/do.wav", "/re.wav", "/mi.wav", "/fa.wav", "/sol.wav", "/la.wav", "/si.wav", "/som_k.wav", "/som_l.wav", "/som_cedilha.wav"};

    public TecladoSom() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        setLayout(new GridLayout(1, TECLAS.length));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);

        for (int i = 0; i < SOMS.length; i++) {
            JButton botao = new JButton(TECLAS[i]);
            botao.addKeyListener(this);
            add(botao);
        }

        setVisible(true);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        new TecladoSom();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char tecla = Character.toUpperCase(e.getKeyChar());

        for (int i = 0; i < TECLAS.length; i++) {
            if (tecla == TECLAS[i].charAt(0)) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(SOMS[i]));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Tecla " + TECLAS[i] + " pressionada");
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
