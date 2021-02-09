/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include <stdio.h>

#define MAX 10

typedef struct {
    int Id;
    double preco;
} WebData;

typedef struct {
    WebData itens[MAX];
    int fim;
} FILA;

FILA* inicializa() {
    FILA* fila;
    fila = malloc(sizeof (FILA));
    if (fila == NULL) {
        printf("Erro ao inicializar fila!\n\n");
    }
    fila->fim = 0;
    return fila;
}

int insere(FILA* fila, int Id, double preco) {
    if (fila == NULL) {
        printf("Fila nao inicializada!\n\n");
        return -1;
    }
    if (fila->fim == MAX) {
        printf("Fila cheia! \n\n");
        return -2;
    }
   fila->itens[fila->fim].Id = Id;
   fila->itens[fila->fim].preco = preco;
   fila->fim++; 
   printf("Elemento Inserido: Id: %d e preço: %lf \n",fila->itens[fila->fim-1].Id,fila->itens[fila->fim-1].preco);
   return 1;
}

int remover(FILA* fila) {
    if (fila == NULL) {
        printf("Fila nao inicializada!\n\n");
        return -1;
    }

   
    if (    fila->fim == 0) {
        printf("Fila vazia!\n\n");
        return -2;
    }
   
    fila->fim--;
    int temp;
    for (temp = 0; temp < (fila->fim); temp++) {
        fila->itens[temp] = fila->itens[temp+1];
     
    }
    return 1;
}

void exercicio2() {
    printf("*****************Exercicio 2 ************************\n");
    
    FILA* pontFila = inicializa();
    remover(pontFila);
    insere(pontFila,1,100);
    insere(pontFila,2,200);
  
}