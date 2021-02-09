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
//typedef FILA3 FILA4;

int inserir4(FILA3* fila, int userId) {
    if (fila == NULL) {
        printf("Fila nao inicializada!\n\n");
        return -1;
    }
    if (fila->count == fila->capacidade) {
        printf("Fila cheia! \n\n");
        return -2;
    }
    fila->users[fila->fim] = userId;
    printf("Inserindo item na fila, posição:%d e Id:%d \n",fila->fim,fila->users[fila->fim]);
    fila->count++; // primeira lacuna
    fila->fim++;
    if (fila->fim == fila->capacidade) {
         fila->fim = 0; //segunda lacuna
    }
    return 1;
}

int remove4(FILA3* fila) {
    if (fila == NULL) {
        printf("Fila nao inicializada!\n\n");
        return -1;
    }
    if (fila->count == 0) {
        printf("Fila vazia! \n\n");
        return -2;
    }
    fila->count--; // terceira lacuna
    printf("Removendo item da fila, posição:%d e Id:%d \n",fila->inicio,fila->users[fila->inicio]);
    fila->inicio++;
    if (fila->inicio == fila->capacidade) {
         fila->inicio = 0; //quarta lacuna
    }
    return 1;
}

void exercicio4() {
    printf("*****************Exercicio 4 ************************\n");
    FILA3* pontfila = inicializa3(3);
    inserir4(pontfila,1);
    inserir4(pontfila,2);
    inserir4(pontfila,3);
    inserir4(pontfila,4);
    remove4(pontfila);
    remove4(pontfila);
    remove4(pontfila);
    remove4(pontfila);
    
}
