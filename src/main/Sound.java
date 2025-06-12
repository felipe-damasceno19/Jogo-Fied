package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/Undertale_waterfall.wav");
        soundURL[1] = getClass().getResource("/sound/aquatic_ambience.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/endMusic.wav");
        soundURL[5] = getClass().getResource("/sound/snd_txt_ismael.wav");
        soundURL[6] = getClass().getResource("/sound/grito.wav");
        soundURL[7] = getClass().getResource("/sound/telefone.wav");
        soundURL[8] = getClass().getResource("/sound/batida-na-porta.wav");
        soundURL[9] = getClass().getResource("/sound/snd_floweytalk1.wav");
    }

    public void setFile(int i) {
        try {
            if (soundURL[i] == null) {
                System.out.println("⚠️ Arquivo de som não encontrado no índice: " + i);
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch (Exception e) {
            System.out.println("⚠️ Erro ao carregar som no índice: " + i);
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
    public void checkVolume() {
    	
    	switch(volumeScale) {
    	case 0: volume = -80f; break;
    	case 1: volume = -20f; break;
    	case 2: volume = -12f; break;
    	case 3: volume = -5f; break;
    	case 4: volume = 1f; break;
    	case 5: volume = 6f; break;
    	}
    	fc.setValue(volume);
    }
}
