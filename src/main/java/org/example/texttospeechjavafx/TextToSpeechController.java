package org.example.texttospeechjavafx;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;

public class TextToSpeechController {

    // Initialise the voice manager
    private static final VoiceManager voiceManager = VoiceManager.getInstance();

    public static ArrayList<String> getVoices() {
        // Set the property for available voices
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Create a list of available voices
        ArrayList<String> voices = new ArrayList<>();
        for(Voice voice : voiceManager.getVoices()){
            voices.add(voice.getName());
        }

        return voices;
    }

    public static ArrayList<String> getSpeedRates() {
        // Create a list of speed rates
        ArrayList<String> speedRates = new ArrayList<>();
        speedRates.add("60"); // very slow
        speedRates.add("100"); // slow
        speedRates.add("140"); // normal
        speedRates.add("170"); // fast
        speedRates.add("200"); // very fast
        return speedRates;
    }

    public static ArrayList<String> getVolumeLevels() {
        // Create a list of volume levels
        ArrayList<String> volumeLevels = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            volumeLevels.add(Integer.toString(i));
        }
        return volumeLevels;
    }

    public static void speak(String message, String voiceType, String rate, String volume) {
        // Get the specified voice
        Voice voice = voiceManager.getVoice(voiceType);
        if (voice == null) {
            System.err.println("Cannot find voice: " + voiceType);
            return;
        }

        try {
            // Allocate resources for the voice
            voice.allocate();
            // Set the speed (rate) of speech
            voice.setRate(Integer.parseInt(rate));
            // Set the volume level
            voice.setVolume(Integer.parseInt(volume));
            // Perform text-to-speech
            voice.speak(message);
        } catch (Exception e) {
            // Print any exceptions that occur
            e.printStackTrace();
        } finally {
            // Deallocate resources for the voice
            voice.deallocate();
        }
    }
}