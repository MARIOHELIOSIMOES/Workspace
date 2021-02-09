/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <stdio.h>
typedef struct Node{
int valor;
 struct Node* pProx; }Element;
void printPilha(Element* inicio)
{
 Element* temp = inicio;
 if (temp == NULL){
 printf("Pilha vazia!\n"); }
 else{
 printf("Conteúdo da Pilha: ");
while (temp != NULL){
 printf("%d, ", temp->valor);
 temp = temp->pProx;}
printf("\b\b.\n\n");
}
 }
void push(Element** inicio, int valor)
{
 Element* nextNode;
 nextNode = malloc(sizeof(Element));
if (nextNode == NULL){
 printf("Erro ao inicializar no!\n");
 return; }
 nextNode->valor = valor;
 nextNode->pProx = *inicio;
 *inicio = nextNode;
 return;
 }
int pop(Element** inicio)
{
 Element* temp;
 temp = *inicio;
 if (temp == NULL){
printf("Pilha vazia\n");
 return 0; }
 else {
 int valor = temp->valor;
 *inicio = temp->pProx;
 free(temp);
 printf("Valor %d removido.\n\n", valor);
return valor; }
 }


void exercicio1(){
    printf("******************EXERCICIO 1! ****************************\n");
    
 int dado;
Element* pilha = NULL;
 push(&pilha, 3);
 push(&pilha, 2);
 push(&pilha, 7);
 push(&pilha, 5);
 dado = pop(&pilha);
 dado = pop(&pilha);
 printPilha(pilha);
 push(&pilha, 9);
 printPilha(pilha);
printf("****************************************************************\n");
}

