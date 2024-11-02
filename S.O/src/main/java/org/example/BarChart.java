package org.example;

import javax.swing.*;
import java.awt.*;

class GraficoBarras extends JPanel {
    private final PoliticaSubstituicaoPaginas algoritmos;

    public GraficoBarras(PoliticaSubstituicaoPaginas algoritmos) {
        this.algoritmos = algoritmos;
    }

    @Override
    protected void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);

        int larguraPainel = getWidth();
        int alturaPainel = getHeight();
        int numeroDeBarras = 4;
        int larguraBarra = larguraPainel / (numeroDeBarras + 1);
        int espacoLateral = (larguraPainel - numeroDeBarras * larguraBarra) / 2;

        Color corFifo = new Color(128, 0, 128);
        Color corLru = new Color(255, 165, 0);
        Color corClock = new Color(75, 0, 130);
        Color corOptimal = new Color(0, 128, 128);

        int falhasFifo = algoritmos.getFalhasPaginaFifo();
        int falhasLru = algoritmos.getFalhasPaginaLru();
        int falhasClock = algoritmos.getFalhasPaginaClock();
        int falhasOptimal = algoritmos.getFalhasPaginaOtimo();

        int maximoFalhas = Math.max(Math.max(falhasFifo, falhasLru), Math.max(falhasClock, falhasOptimal));
        int fatorDeEscala = (alturaPainel - 60) / maximoFalhas;

        int alturaBarraFifo = falhasFifo * fatorDeEscala;
        grafico.setColor(corFifo);
        grafico.fillRect(espacoLateral, alturaPainel - alturaBarraFifo - 40, larguraBarra - 10, alturaBarraFifo);

        int alturaBarraLru = falhasLru * fatorDeEscala;
        grafico.setColor(corLru);
        grafico.fillRect(espacoLateral + larguraBarra, alturaPainel - alturaBarraLru - 40, larguraBarra - 10, alturaBarraLru);

        int alturaBarraClock = falhasClock * fatorDeEscala;
        grafico.setColor(corClock);
        grafico.fillRect(espacoLateral + 2 * larguraBarra, alturaPainel - alturaBarraClock - 40, larguraBarra - 10, alturaBarraClock);

        int alturaBarraOptimal = falhasOptimal * fatorDeEscala;
        grafico.setColor(corOptimal);
        grafico.fillRect(espacoLateral + 3 * larguraBarra, alturaPainel - alturaBarraOptimal - 40, larguraBarra - 10, alturaBarraOptimal);

        grafico.setColor(Color.BLACK);
        grafico.drawString("FIFO: " + falhasFifo + " faults", espacoLateral, alturaPainel - 20);
        grafico.drawString("LRU: " + falhasLru + " faults", espacoLateral + larguraBarra, alturaPainel - 20);
        grafico.drawString("Clock: " + falhasClock + " faults", espacoLateral + 2 * larguraBarra, alturaPainel - 20);
        grafico.drawString("Optimal: " + falhasOptimal + " faults", espacoLateral + 3 * larguraBarra, alturaPainel - 20);
    }
}
