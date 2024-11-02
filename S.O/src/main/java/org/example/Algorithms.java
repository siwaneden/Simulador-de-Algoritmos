package org.example;

import java.util.LinkedList;

 class PoliticaSubstituicaoPaginas {
    int[] referencias;
    int capacidadeQuadros;

    public PoliticaSubstituicaoPaginas(int[] referencias, int capacidadeQuadros) {
        this.referencias = referencias;
        this.capacidadeQuadros = capacidadeQuadros;
    }

    public int fifo() {
        LinkedList<Integer> memoria = new LinkedList<>();
        int falhasPagina = 0;

        for (int referencia : referencias) {
            if (!memoria.contains(referencia)) {
                if (memoria.size() == capacidadeQuadros) {
                    memoria.poll();
                }
                memoria.add(referencia);
                falhasPagina++;
            }
        }

        return falhasPagina;
    }

    public int lru() {
        LinkedList<Integer> memoria = new LinkedList<>();
        int falhasPagina = 0;

        for (int referencia : referencias) {
            if (!memoria.contains(referencia)) {
                if (memoria.size() == capacidadeQuadros) {
                    memoria.poll();
                }
                memoria.add(referencia);
                falhasPagina++;
            } else {
                memoria.remove((Integer) referencia);
                memoria.add(referencia);
            }
        }
        return falhasPagina;
    }

    public int clock() {
        LinkedList<Integer> memoria = new LinkedList<>();
        LinkedList<Integer> bitsControle = new LinkedList<>();
        int falhasPagina = 0;
        int bitAtivo = 1;
        int bitInativo = 0;
        int ponteiro = 0;

        for (int referencia : referencias) {
            if (!memoria.contains(referencia)) {
                if (memoria.size() == capacidadeQuadros) {
                    while (true) {
                        if (ponteiro == capacidadeQuadros) {
                            ponteiro = 0;
                        }
                        if (bitsControle.get(ponteiro) == bitAtivo) {
                            bitsControle.set(ponteiro, bitInativo);
                            ponteiro++;
                        } else {
                            memoria.set(ponteiro, referencia);
                            bitsControle.set(ponteiro, bitAtivo);
                            falhasPagina++;
                            ponteiro++;
                            break;
                        }
                    }
                } else {
                    memoria.add(referencia);
                    bitsControle.add(bitAtivo);
                    falhasPagina++;
                }
            } else {
                int indice = memoria.indexOf(referencia);
                bitsControle.set(indice, bitAtivo);
            }
        }
        return falhasPagina;
    }

    public int otimo() {
        int[] quadros = new int[capacidadeQuadros];
        int acertos = 0;
        int indice = 0;
        for (int i = 0; i < referencias.length; i++) {
            boolean encontrado = false;
            for (int k = 0; k < quadros.length; k++) {
                if (quadros[k] == referencias[i]) {
                    encontrado = true;
                    acertos++;
                    break;
                }
            }
            if (encontrado) continue;

            if (indice < capacidadeQuadros)
                quadros[indice++] = referencias[i];
            else {
                int substituir = -1, maisDistante = i + 1;
                for (int j = 0; j < quadros.length; j++) {
                    int k;
                    for (k = i + 1; k < referencias.length; k++) {
                        if (quadros[j] == referencias[k]) {
                            if (k > maisDistante) {
                                maisDistante = k;
                                substituir = j;
                            }
                            break;
                        }
                    }
                    if (k == referencias.length) {
                        substituir = j;
                        break;
                    }
                }
                quadros[substituir == -1 ? 0 : substituir] = referencias[i];
            }
        }
        return referencias.length - acertos;
    }

    public int getFalhasPaginaFifo() {
        return fifo();
    }

    public int getFalhasPaginaLru() {
        return lru();
    }

    public int getFalhasPaginaClock() {
        return clock();
    }

    public int getFalhasPaginaOtimo() {
        return otimo();
    }

    @Override
    public String toString() {
        StringBuilder descricao = new StringBuilder();
        descricao.append("===== Detalhes dos Algoritmos de Substituição de Página =====\n");

        descricao.append("\nReferências de Páginas: ");
        for (int i = 0; i < referencias.length; i++) {
            descricao.append(referencias[i]);
            if (i < referencias.length - 1) {
                descricao.append(", ");
            }
        }

        descricao.append("\nCapacidade de Quadros: ").append(capacidadeQuadros).append("\n");

        descricao.append("\n----- Resultados de Falhas de Página -----\n");
        descricao.append("FIFO: ").append(getFalhasPaginaFifo()).append(" falhas\n");
        descricao.append("LRU: ").append(getFalhasPaginaLru()).append(" falhas\n");
        descricao.append("Clock: ").append(getFalhasPaginaClock()).append(" falhas\n");
        descricao.append("Ótimo: ").append(getFalhasPaginaOtimo()).append(" falhas\n");

        descricao.append("\n===========================================================\n");

        return descricao.toString();
    }
 }
