/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <stdio.h>

typedef struct {
    int* users;
    int inicio, fim;
    int count, capacidade; //declaração do count
} FILA3;

FILA3* inicializa3(int capacidade) {
    FILA3* fila;
    fila = malloc(sizeof(FILA3));
    if (fila == NULL) {
        printf("Erro ao inicializar fila!\n\n");
    }
    fila->inicio = 0;
    fila->fim = 0;
    fila->count = 0; /// não há a declaração do atributo count na struct Fila
    fila->capacidade = capacidade;
    if (capacidade > 0) {
        fila->users = malloc(capacidade*sizeof(fila->users)); // Resposta do Exercício
    } else {
        printf("Capacidade invalida!");
    }
    if (fila->users == NULL) {
        printf("Erro ao alocar espaço para a fila!\n\n");
    }
    return fila;
}

void exercicio3() {
    printf("*****************Exercicio 3 ************************\n");

    FILA3* pontfila = inicializa3(10);
    pontfila->users[1]=1;
    pontfila->users[2]=20;
    pontfila->users[3]=1;
    pontfila->users[4]=20;
    pontfila->users[5]=1;
    pontfila->users[6]=20;
    pontfila->users[7]=1;
    pontfila->users[8]=20;
    pontfila->users[9]=1;
    pontfila->users[10]=20;
    pontfila->users[0]=1;
    pontfila->users[11]=30;
    
    printf("user1 =%d",pontfila->users[10]);
            
}
