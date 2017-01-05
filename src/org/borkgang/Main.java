package org.borkgang;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(final String[] args) throws Exception {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("media/audio.wav"));

        try {
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.start();
        } catch (Exception e) {}

        JOptionPane.showMessageDialog(null, "cracked by unreal @ borkgang");
        System.out.println("spoofing. make sure skype is closed :)");
        final HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/", (HttpHandler) new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(final HttpExchange t) throws IOException {
            final String response = "@HgMRi5Kdx^fMA4pi6!NYUEutax4UaD#7Iu";
            t.sendResponseHeaders(200, response.length());
            final OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            System.out.println("frickin borked a hecking packet...");
        }
    }
}
