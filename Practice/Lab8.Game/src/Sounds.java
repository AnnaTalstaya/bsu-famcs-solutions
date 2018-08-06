import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private String track;
    private Clip clip = null;
    private FloatControl volumeC = null;
    private double wt; //уровень громкости
    private boolean plAudio;

    public Sounds(String track, double wt){
        this.track = track;
        this.wt = wt;
        plAudio = false;
    }

    public void play(){
        File f = new File(this.track);
        AudioInputStream tr = null;

        try{
            tr = AudioSystem.getAudioInputStream(f);
        }catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            clip = AudioSystem.getClip();
            clip.open(tr);

            volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); //gain -усиление

            if(!plAudio){
                clip.setFramePosition(0); //устанавливаем указатель на старт
                clip.start();  //начали
                plAudio = true;
            }
            else{
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                plAudio = true;
            }
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        clip.stop();
        clip.close();
        this.plAudio = false;
    }

    public void setVolume(){
        if(wt<0) wt = 0;
        if(wt>1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min)*(float)wt +min);
    }

    public void repeat(){
        if(this.plAudio){
            clip.loop(100); //loop-петля
        }
    }

}
